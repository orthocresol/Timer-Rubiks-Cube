package org.timerrubikscube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.timerrubikscube.fragments.*
import org.timerrubikscube.fragments.adapters.ViewPagerAdapter

class HomeScreen : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        initVariable()
        setUpTabs()
    }

    private fun initVariable() {
        viewPager = findViewById(R.id.home_screen_viewpager)
        tabLayout = findViewById(R.id.home_screen_tab_layout)
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TimerFragment(), "Timer")
        adapter.addFragment(StatFragment(), "Statistics")
        adapter.addFragment(RecordFragment(), "Records")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_timer_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_statistics)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_records)
    }
}