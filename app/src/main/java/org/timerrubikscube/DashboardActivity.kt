package org.timerrubikscube

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.yashovardhan99.timeit.Stopwatch
import org.timerrubikscube.nonactivityclass.FirestoreAdapterForDashboard
import org.timerrubikscube.nonactivityclass.Item
import org.timerrubikscube.nonactivityclass.ScrambleGenerator
import org.timerrubikscube.nonactivityclass.Session
import java.util.*

class DashboardActivity : AppCompatActivity() {

    lateinit var tvScramble: TextView
    lateinit var nextBtn: ImageButton
    lateinit var tvTime: TextView
    lateinit var startTimeBtn: Button
    lateinit var recyclerView: RecyclerView

    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var deleteImgBtn: ImageButton
    lateinit var plus2Btn: MaterialButton

    lateinit var stopwatch: Stopwatch
    lateinit var relativeLayout: RelativeLayout
    lateinit var addSessionBtn: FloatingActionButton

    var averageOf5: TextView? = null
    var averageOf12: TextView? = null
    var averageOf50: TextView? = null

    var currentItem: Item? = null

    var db = FirebaseFirestore.getInstance()
    val userID = FirebaseAuth.getInstance().currentUser?.uid
    var collectionReference = db.collection("Time $userID")
    var adapter: FirestoreAdapterForDashboard? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initVariables()
        clickListeners()
        setSessionNumber()
        showAverage()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }

    private fun setSessionNumber() {
        val documentRef = db.collection("session").document(userID!!)
        documentRef
            .get()
            .addOnSuccessListener(OnSuccessListener {
                if (it.exists()) {
                    val session = it.toObject(Session::class.java)
                    val sessionIDtoString = session?.id.toString()
                    collectionReference =
                        db.collection("Time").document(userID!!).collection(sessionIDtoString)
                }
            })
    }

    private fun setupRecyclerView() {

        val query =
            collectionReference.orderBy("timeFromBeginning", Query.Direction.DESCENDING).limit(12)
        val options = FirestoreRecyclerOptions.Builder<Item>()
            .setQuery(query, Item::class.java)
            .build()

        adapter = FirestoreAdapterForDashboard(options)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_dashboard)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter?.startListening()
    }

    private fun clickListeners() {
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuitem ->
            when (menuitem.itemId) {
                R.id.nav_menu_star -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_menu_history -> {
                    startActivity(Intent(this@DashboardActivity, HistoryActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_menu_settings -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else -> {

                }
            }
            true
        })

        nextBtn.setOnClickListener(View.OnClickListener {
            var scramble = ScrambleGenerator().giveScramble()
            tvScramble.text = scramble
        })

        deleteImgBtn.setOnClickListener(View.OnClickListener {

            collectionReference
                .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    for (documentSnapshot in queryDocumentSnapshots) {
                        val item = documentSnapshot.toObject(
                            Item::class.java
                        )
                        item.id = documentSnapshot.id
                        collectionReference.document(item.id).delete()
                    }
                    showAverage()
                }
        })

        plus2Btn.setOnClickListener(View.OnClickListener {
            collectionReference
                .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener { queryDocumentSnapshots ->
                    for (documentSnapshot in queryDocumentSnapshots) {
                        val item = documentSnapshot.toObject(
                            Item::class.java
                        )
                        item.id = documentSnapshot.id
                        val float: Float = 2.00f + currentItem!!.timing
                        val itemToSend = Item(
                            float,
                            currentItem?.timestamp,
                            currentItem!!.timeFromBeginning,
                            currentItem?.scramble
                        )
                        collectionReference.document(item.id).set(itemToSend)
                    }
                    showAverage()
                }
        })


        startTimeBtn.setOnClickListener(View.OnClickListener {
            if (!stopwatch.isStarted) {
                stopwatch.start()

                makeElementsInvisible()
                tvTime.setTextSize(100F)
            }
        })
        relativeLayout.setOnClickListener(View.OnClickListener {
            if (stopwatch.isStarted) {
                setupRecyclerView()

                showAverage()
                val time = tvTime.text.toString()
                val floatTime = time.toFloat()
                currentItem =
                    Item(floatTime, Date(), System.currentTimeMillis(), tvScramble.text.toString())
                collectionReference.add(currentItem!!)
                tvScramble.text = ScrambleGenerator().giveScramble()
                stopwatch.stop()
                makeElementsVisible()
                tvTime.setTextSize(70F)
            }
        })
        addSessionBtn.setOnClickListener(View.OnClickListener {
            val documentRef = db.collection("session").document(userID!!)
            documentRef
                .get()
                .addOnSuccessListener(OnSuccessListener {
                    if (it.exists()) {
                        val session = it.toObject(Session::class.java)
                        val nextID = session?.id?.plus(1)
                        documentRef.set(Session("session_name", nextID!!))
                        val sessionIDtoString = nextID.toString()
                        collectionReference =
                            db.collection("Time").document(userID!!).collection(sessionIDtoString)
                    }
                })
        })
    }

    private fun makeElementsInvisible() {
        tvScramble.visibility = View.INVISIBLE
        nextBtn.visibility = View.INVISIBLE
        tvTime.setTextColor(ContextCompat.getColor(this, R.color.green_800))
        relativeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        toolbar.visibility = View.INVISIBLE
        startTimeBtn.visibility = View.INVISIBLE
        recyclerView?.visibility = View.INVISIBLE
        averageOf5?.visibility = View.INVISIBLE
        averageOf12?.visibility = View.INVISIBLE
        averageOf50?.visibility = View.INVISIBLE
        deleteImgBtn.visibility = View.INVISIBLE
        plus2Btn.visibility = View.INVISIBLE
        addSessionBtn.visibility = View.INVISIBLE
    }

    private fun makeElementsVisible() {
        tvScramble.visibility = View.VISIBLE
        nextBtn.visibility = View.VISIBLE
        tvTime.setTextColor(ContextCompat.getColor(this, R.color.black))
        relativeLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        toolbar.visibility = View.VISIBLE
        startTimeBtn.visibility = View.VISIBLE
        recyclerView?.visibility = View.VISIBLE
        averageOf5?.visibility = View.VISIBLE
        averageOf12?.visibility = View.VISIBLE
        averageOf50?.visibility = View.VISIBLE
        deleteImgBtn.visibility = View.VISIBLE
        plus2Btn.visibility = View.VISIBLE
        addSessionBtn.visibility = View.VISIBLE
    }

    private fun initVariables() {
        tvScramble = findViewById(R.id.dashboard_scramble_tv)
        nextBtn = findViewById(R.id.dashboard_next)
        tvTime = findViewById(R.id.dashboard_time)
        startTimeBtn = findViewById(R.id.dashboard_play)
        toolbar = findViewById(R.id.dashboard_toolbar)
        relativeLayout = findViewById(R.id.dashboard_relative_layout)
        deleteImgBtn = findViewById(R.id.dashboard_delete)
        plus2Btn = findViewById(R.id.dashboard_plus2Button)
        averageOf5 = findViewById(R.id.dashboard_averageOf5)
        averageOf12 = findViewById(R.id.dashboard_averageOf12)
        averageOf50 = findViewById(R.id.dashboard_averageOf50)
        addSessionBtn = findViewById(R.id.dashboard_addSession)
        setSupportActionBar(toolbar)
        navigationView = findViewById(R.id.nav_view_dashboard)
        drawerLayout = findViewById(R.id.drawer_dashboard)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        tvScramble.text = ScrambleGenerator().giveScramble()
        stopwatch = Stopwatch()
        stopwatch.setTextView(tvTime)
    }

    private fun showAverage() {
        val timings = ArrayList<Item>()

        collectionReference
            .orderBy("timeFromBeginning", Query.Direction.DESCENDING)
            .limit(50)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                for (documentSnapshot in queryDocumentSnapshots) {
                    val item = documentSnapshot.toObject(
                        Item::class.java
                    )
                    item.id = documentSnapshot.id
                    timings.add(item)
                }
                Log.d("size", "showAverage: " + timings.size)
                if (timings.size < 5) {
                    averageOf5?.text = "DNF"
                    averageOf12?.text = "DNF"
                    averageOf50?.text = "DNF"
                } else if (timings.size < 12) {
                    averageOf12?.text = "DNF"
                    averageOf50?.text = "DNF"
                    bestOf3(timings)
                } else if (timings.size < 50) {
                    averageOf50?.text = "DNF"
                    bestOf3(timings)
                    bestOf10(timings)
                } else if (timings.size == 50) {
                    bestOf3(timings)
                    bestOf10(timings)
                    bestOf48(timings)
                }
            }
    }

    private fun bestOf48(timings: ArrayList<Item>) {
        val arrayo48 = ArrayList<Float>()

        for (i in 0..49) {
            arrayo48.add(timings[i].timing)
        }

        Collections.sort(arrayo48)
        var result = 0.00f
        for (i in 1..48) {
            result += arrayo48[i]
        }
        result /= 48
        val toShow = String.format("%.2f", result)
        averageOf50?.text = toShow
    }

    private fun bestOf10(timings: ArrayList<Item>) {
        val arrayo10 = ArrayList<Float>()

        for (i in 0..11) {
            arrayo10.add(timings[i].timing)
        }

        Collections.sort(arrayo10)
        var result = 0.00f
        for (i in 1..10) {
            result += arrayo10[i]
        }
        result /= 10

        val toShow = String.format("%.2f", result)
        averageOf12?.text = toShow

    }

    private fun bestOf3(timings: ArrayList<Item>) {
        val arrayo3 = ArrayList<Float>()

        for (i in 0..4) {
            arrayo3.add(timings[i].timing)
        }

        Collections.sort(arrayo3)
        var result = 0.00f
        for (i in 1..3) {
            result += arrayo3[i]
        }
        result /= 3

        val toShow = String.format("%.2f", result)
        averageOf5?.text = toShow
    }
}