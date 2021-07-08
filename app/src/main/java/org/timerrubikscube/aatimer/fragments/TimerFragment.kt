package org.timerrubikscube.aatimer.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yashovardhan99.timeit.Stopwatch
import com.yashovardhan99.timeit.Timer
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.nonactivityclass.Item
import org.timerrubikscube.aatimer.nonactivityclass.ScrambleGenerator
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TimerFragment : Fragment() {
    lateinit var scramble: TextView
    lateinit var nextScrambleBtn: ImageButton
    lateinit var timeTv: TextView
    lateinit var goBtn: AppCompatButton
    lateinit var layout: RelativeLayout
    lateinit var stopwatch: Stopwatch
    lateinit var alertTv : TextView
    lateinit var ao5Tv : TextView
    lateinit var ao12Tv : TextView
    var timer: Timer = Timer(15000)
    lateinit var _context: Context
    lateinit var sw_inspection: SwitchMaterial
    var isInspectionOn = true
    var isInspecting = false
    var isRunning = false
    var listener : FragmentTimerListener? = null




    var userID = FirebaseAuth.getInstance().currentUser?.uid
    var db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("Solve $userID")

    interface FragmentTimerListener {
        fun hideBottomNav()
        fun showBottomNav()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentTimerListener) {
            listener = context
        }

    }

    override fun onResume() {
        super.onResume()
        getItems()
    }

    private fun checkForARecord() {
        var item : Item
        collectionRef
            .orderBy("timing", Query.Direction.ASCENDING)
            .limit(1)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    item = documentSnapshot.toObject(Item::class.java)
                    val currentTime = timeTv.text.toString().toFloat()
                    Log.d("oma", "checkForARecord: $currentTime ${item.timing}")
                    if(currentTime <= item.timing){
                        //it's a record
                        showSnackbarForRecord()
                    }

                }

            }
    }

    private fun showSnackbarForRecord() {
        Log.d("oma", "checkForARecordgffg")
        Snackbar.make(layout, "New Record !", Snackbar.LENGTH_LONG)
            .setAction("Close", View.OnClickListener {

            })
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            .show()
    }

    private fun getItems() {
        val itemList = ArrayList<Item>()
        collectionRef
            .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
            .limit(12)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    val item = documentSnapshot.toObject(Item::class.java)
                    item.id = documentSnapshot.id
                    itemList.add(item)
                }

                findCurrentao5(itemList)
                findCurrentao12(itemList)
            }
    }
    private fun findCurrentao5(itemList: ArrayList<Item>) {
        if(itemList.size < 5){
            ao5Tv.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        for(i in 0..4){
            toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for(j in 1..3){
            aggregate += toSort[j]
        }
        aggregate /= 3
        ao5Tv.text = String.format("%.2f", aggregate)
    }

    private fun findCurrentao12(itemList: ArrayList<Item>) {
        if(itemList.size < 12){
            ao12Tv.text = "DNF"
            return
        }
        val toSort = ArrayList<Float>()
        for(i in 0..11){
            toSort.add(itemList[i].timing)
        }
        Collections.sort(toSort)
        var aggregate = 0.00f
        for(j in 1..10){
            aggregate += toSort[j]
        }
        aggregate /= 10
        ao12Tv.text = String.format("%.2f", aggregate)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_timer, container, false)
        initVariable(view)
        clickListeners()

        return view
    }

    private fun clickListeners() {
        nextScrambleBtn.setOnClickListener(View.OnClickListener {
            scramble.text = ScrambleGenerator().giveScramble()
        })
        goBtn.setOnLongClickListener(OnLongClickListener {
            if (isInspectionOn && !isInspecting) {
                // nothing here
            } else {
                disappearElements()
                listener?.hideBottomNav()
            }
            false
        })
        goBtn.setOnClickListener() {
            if (isInspectionOn && !isInspecting) {
                isInspecting = true;
                layout.setBackgroundColor(ContextCompat.getColor(_context, R.color.yellow))
                timer.start()

                timeTv.setTextColor(ContextCompat.getColor(_context, R.color.red))

            } else if (!stopwatch.isStarted && isRunning) {
                if (isInspectionOn && timer.remainingTime > 0) timer.stop()
                stopwatch.start()

                alertTv.visibility = View.INVISIBLE
                timeTv.setTextColor(ContextCompat.getColor(_context, R.color.black))
            }
        }
        layout.setOnClickListener(View.OnClickListener {
            if (stopwatch.isStarted) {
                stopwatch.stop()
                recordSolve()
                reappearElements()
                listener?.showBottomNav()
            }
        })
        sw_inspection.setOnClickListener(View.OnClickListener {
            isInspectionOn = sw_inspection.isChecked
        })

        timer.setOnTickListener(object : Timer.OnTickListener {
            override fun onTick(timer: Timer) {
                val remTime = timer.remainingTime
                if (remTime > 7500 && remTime < 8500) {
                    alertTv.text = "8s!"
                    alertTv.visibility = View.VISIBLE
                }
                if (remTime > 3000 && remTime < 3500) {
                    alertTv.text = "GO !!!"
                }
            }

            override fun onComplete(timer: Timer) {
                alertTv.text = "DNF"
            }
        })
    }


    private fun recordSolve() {
        getItems()
        checkForARecord()
        val strScramble = scramble.text.toString()

        val dateFormat: DateFormat = SimpleDateFormat("hh:mm dd-MMM")
        val dateStr = dateFormat.format(Date())

        val timing = timeTv.text.toString().toFloat()
        val timeFromBeginning = System.currentTimeMillis()
        val item = Item(timing, dateStr, timeFromBeginning, strScramble)
        db.collection("Solve $userID").add(item)
    }

    private fun reappearElements() {
        isRunning = false
        sw_inspection.visibility = View.VISIBLE
        ao5Tv.visibility = View.VISIBLE
        ao12Tv.visibility = View.VISIBLE
        if (isInspectionOn) isInspecting = false;
        scramble.text = ScrambleGenerator().giveScramble()
        scramble.visibility = View.VISIBLE
        goBtn.visibility = View.VISIBLE
        nextScrambleBtn.visibility = View.VISIBLE
        layout.setBackgroundColor(ContextCompat.getColor(_context!!, R.color.white))
        timeTv.setTextSize(70F)
    }

    private fun disappearElements() {
        isRunning = true
        sw_inspection.visibility = View.INVISIBLE
        ao5Tv.visibility = View.INVISIBLE
        ao12Tv.visibility = View.INVISIBLE
        scramble.visibility = View.INVISIBLE
        goBtn.visibility = View.INVISIBLE
        nextScrambleBtn.visibility = View.INVISIBLE
        layout.setBackgroundColor(ContextCompat.getColor(_context!!, R.color.green_300))
        timeTv.setTextSize(100F)
        if(!isInspectionOn) timeTv.setText("00.00");
    }

    private fun initVariable(view: View) {
        scramble = view.findViewById(R.id.timer_scramble_tv)
        scramble.text = ScrambleGenerator().giveScramble()
        ao5Tv = view.findViewById(R.id.timer_ao5)
        ao12Tv = view.findViewById(R.id.timer_ao12)
        nextScrambleBtn = view.findViewById(R.id.timer_next)
        timeTv = view.findViewById(R.id.timer_time)
        goBtn = view.findViewById(R.id.timer_go)
        layout = view.findViewById(R.id.timer_layout)
        stopwatch = Stopwatch()
        stopwatch.setTextView(timeTv)
        timer.setTextView(timeTv)
        alertTv = view.findViewById(R.id.timer_alert_8s)
        alertTv.visibility = View.INVISIBLE
        sw_inspection = view.findViewById(R.id.timer_enable_inspection)
        _context = requireContext()
    }


}