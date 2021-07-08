package org.timerrubikscube.aatimer.stat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class StatViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return StatStatisticsFragment()
            1 -> return StatGraphFragment()
        }
        return StatStatisticsFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Solves"
            1 -> return "Graph"
        }
        return "Statistics"
    }
}