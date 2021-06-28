package org.timerrubikscube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.timerrubikscube.fragments.SignInFragment
import org.timerrubikscube.fragments.SignUpFragment
import org.timerrubikscube.fragments.adapters.ViewPagerAdapter

class SignInSignUpActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_sign_up)

        initVariable()
        setUpTabs()
    }

    private fun initVariable() {
        viewPager = findViewById(R.id.sign_in_sign_up_view_pager)
        tabLayout = findViewById(R.id.sign_in_sign_up_tab_layout)
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(SignInFragment(), "Login")
        adapter.addFragment(SignUpFragment(), "Register")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_login_24)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_how_to_reg_24)
    }
}