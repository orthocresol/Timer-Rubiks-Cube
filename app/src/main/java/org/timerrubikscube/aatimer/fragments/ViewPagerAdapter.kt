package org.timerrubikscube.aatimer.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        when(position){
            1 -> return TimerFragment()
            0 -> return StatFragment()
            2 -> return RecordFragment()
            3 -> return SettingsFragment()
        }
        return TimerFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}