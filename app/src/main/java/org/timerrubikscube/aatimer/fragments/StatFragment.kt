package org.timerrubikscube.aatimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import org.timerrubikscube.R
import org.timerrubikscube.aatimer.stat.StatViewPagerAdapter
import org.timerrubikscube.aatimer.widget.CustomViewPager

class StatFragment : Fragment() {

    lateinit var mView : View
    lateinit var tabLayout: TabLayout
    lateinit var viewPager : CustomViewPager



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stat, container, false)
        initVariables()

        return mView
    }



    private fun initVariables() {
        tabLayout = mView.findViewById(R.id.stat_tabLayout)
        viewPager = mView.findViewById(R.id.stat_viewPager)
        val _adapter = StatViewPagerAdapter(
            childFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        viewPager.adapter = _adapter
        viewPager.setPagingEnabled(false)

        tabLayout.setupWithViewPager(viewPager)
    }
}