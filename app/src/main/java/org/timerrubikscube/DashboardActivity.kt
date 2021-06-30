package org.timerrubikscube

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.yashovardhan99.timeit.Stopwatch
import org.timerrubikscube.nonactivityclass.ScrambleGenerator

class DashboardActivity : AppCompatActivity() {

    lateinit var tvScramble: TextView
    lateinit var nextBtn: ImageButton
    lateinit var tvTime: TextView
    lateinit var startTimeBtn: Button


    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar

    lateinit var stopwatch: Stopwatch
    lateinit var relativeLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initVariables()
        clickListeners()







    }

    private fun clickListeners() {
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuitem ->
            when (menuitem.itemId) {
                R.id.nav_menu_star -> {
                    println("star")
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_menu_history -> {
                    println("history")
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_menu_settings -> {
                    println("settings")
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                else -> {
                    println("Rip")
                }
            }
            true
        })

        nextBtn.setOnClickListener(View.OnClickListener {
            var scramble = ScrambleGenerator().giveScramble()
            tvScramble.text = scramble
        })
        startTimeBtn.setOnClickListener(View.OnClickListener {
            if (!stopwatch.isStarted) {
                stopwatch.start()
                tvScramble.text = ScrambleGenerator().giveScramble()
                tvScramble.visibility = View.INVISIBLE
                nextBtn.visibility = View.INVISIBLE
                tvTime.setTextColor(ContextCompat.getColor(this, R.color.green_800))
                toolbar.visibility = View.INVISIBLE
                startTimeBtn.visibility = View.INVISIBLE
                tvTime.setTextSize(100F)
            }
        })
        relativeLayout.setOnClickListener(View.OnClickListener {
            stopwatch.stop()
            tvScramble.visibility = View.VISIBLE
            nextBtn.visibility = View.VISIBLE
            tvTime.setTextColor(ContextCompat.getColor(this, R.color.black))
            toolbar.visibility = View.VISIBLE
            startTimeBtn.visibility = View.VISIBLE
            tvTime.setTextSize(70F)
        })
    }

    private fun initVariables() {
        tvScramble = findViewById(R.id.dashboard_scramble_tv)
        nextBtn = findViewById(R.id.dashboard_next)
        tvTime = findViewById(R.id.dashboard_time)
        startTimeBtn = findViewById(R.id.dashboard_play)
        toolbar = findViewById(R.id.dashboard_toolbar)
        relativeLayout = findViewById(R.id.dashboard_relative_layout)
        setSupportActionBar(toolbar)
        navigationView = findViewById(R.id.nav_view_dashboard)
        drawerLayout = findViewById(R.id.drawer_dashboard)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        tvScramble.text = ScrambleGenerator().giveScramble()
        stopwatch= Stopwatch()
        stopwatch.setTextView(tvTime)
    }
}