package org.timerrubikscube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import org.timerrubikscube.nonactivityclass.ScrambleGenerator

class DashboardActivity : AppCompatActivity() {

    lateinit var tvScramble: TextView
    lateinit var nextBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvScramble = findViewById(R.id.dashboard_scramble_tv)
        nextBtn = findViewById(R.id.dashboard_next)
        tvScramble.text = ScrambleGenerator().giveScramble()

        nextBtn.setOnClickListener(View.OnClickListener {
            var scramble = ScrambleGenerator().giveScramble()
            tvScramble.text = scramble
        })
    }
}