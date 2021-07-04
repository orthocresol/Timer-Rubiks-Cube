package org.timerrubikscube.fragments

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
import com.google.android.material.switchmaterial.SwitchMaterial
import com.yashovardhan99.timeit.Stopwatch
import com.yashovardhan99.timeit.Timer

import org.timerrubikscube.R
import org.timerrubikscube.nonactivityclass.ScrambleGenerator


class TimerFragment : Fragment() {
    lateinit var scramble: TextView
    lateinit var nextScrambleBtn: ImageButton
    lateinit var timeTv: TextView
    lateinit var goBtn: AppCompatButton
    lateinit var layout: RelativeLayout
    lateinit var stopwatch: Stopwatch
    var timer: Timer = Timer(15000)
    lateinit var _context: Context
    lateinit var sw_inspection: SwitchMaterial
    var isInspectionOn = true
    var isInspecting = false
    var isRunning = false

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
            } else
                disappearElements()
            false
        })
        goBtn.setOnClickListener() {
            if (isInspectionOn && !isInspecting) {
                isInspecting = true;
                timer = Timer(15000)
                timer.setTextView(timeTv)
                timer.start()
                timeTv.setTextColor(ContextCompat.getColor(_context, R.color.red))

            } else if (!stopwatch.isStarted && isRunning) {
                if (isInspectionOn && timer.remainingTime > 0) timer.stop()
                stopwatch.start()
                timeTv.setTextColor(ContextCompat.getColor(_context, R.color.black))
            }
        }
        layout.setOnClickListener(View.OnClickListener {
            if (stopwatch.isStarted) {
                stopwatch.stop()

                reappearElements()
            }
        })
        sw_inspection.setOnClickListener(View.OnClickListener {
            isInspectionOn = sw_inspection.isChecked
        })
    }

    private fun reappearElements() {
        isRunning = false
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
        scramble.visibility = View.INVISIBLE
        goBtn.visibility = View.INVISIBLE
        nextScrambleBtn.visibility = View.INVISIBLE
        layout.setBackgroundColor(ContextCompat.getColor(_context!!, R.color.green_300))
        timeTv.setTextSize(100F)
    }

    private fun initVariable(view: View) {
        scramble = view.findViewById(R.id.timer_scramble_tv)
        scramble.text = ScrambleGenerator().giveScramble()
        nextScrambleBtn = view.findViewById(R.id.timer_next)
        timeTv = view.findViewById(R.id.timer_time)
        goBtn = view.findViewById(R.id.timer_go)
        layout = view.findViewById(R.id.timer_layout)
        stopwatch = Stopwatch()
        stopwatch.setTextView(timeTv)
        timer.setTextView(timeTv)
        sw_inspection = view.findViewById(R.id.timer_enable_inspection)
        _context = requireContext()
    }


}