package org.timerrubikscube.finaldesign.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FinalViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FinalTimerFragment()
            1 -> return FinalStatFragment()
            2 -> return FinalRecordFragment()
        }
        return FinalTimerFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}