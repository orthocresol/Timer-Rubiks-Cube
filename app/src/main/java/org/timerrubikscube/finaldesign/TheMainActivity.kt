package org.timerrubikscube.finaldesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.timerrubikscube.R
import org.timerrubikscube.finaldesign.fragments.FinalViewPagerAdapter

class TheMainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView : BottomNavigationView
    lateinit var viewPager : ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_main)

        initVariables()
        clickListeners()

    }

    private fun clickListeners() {
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> bottomNavigationView.menu.findItem(R.id.timer_bottom_nav).setChecked(true)
                    1 -> bottomNavigationView.menu.findItem(R.id.stat_bottom_nav).setChecked(true)
                    2 -> bottomNavigationView.menu.findItem(R.id.record_bottom_nav).setChecked(true)
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.timer_bottom_nav -> viewPager.currentItem = 0
                R.id.stat_bottom_nav -> viewPager.currentItem = 1
                R.id.record_bottom_nav -> viewPager.currentItem = 2
            }
            true
        }
    }

    private fun initVariables() {
        bottomNavigationView = findViewById(R.id.main_bottom_nav)
        viewPager = findViewById(R.id.main_viewpager)
        val _adapter = FinalViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = _adapter
    }

}