package org.timerrubikscube.finaldesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.timerrubikscube.R;
import org.timerrubikscube.finaldesign.fragments.FinalTimerFragment;
import org.timerrubikscube.finaldesign.fragments.FinalViewPagerAdapter;

public class TheMainActivityCustomNavJava extends AppCompatActivity implements FinalTimerFragment.FragmentTimerListener {
    ChipNavigationBar bottomNavigationBar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_main_custom_nav);

        initVariables();
        clickListeners();

    }

    private void clickListeners() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 1:
                        bottomNavigationBar.setItemSelected(R.id.timer_bottom_nav, true);
                        break;
                    case 0:
                        bottomNavigationBar.setItemSelected(R.id.stat_bottom_nav, true);
                        break;
                    case 2:
                        bottomNavigationBar.setItemSelected(R.id.record_bottom_nav, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.timer_bottom_nav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.stat_bottom_nav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.record_bottom_nav:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void initVariables() {
        bottomNavigationBar = findViewById(R.id.main_custom_bottom_nav);
        viewPager = findViewById(R.id.main_custom_viewpager);
        FinalViewPagerAdapter _adapter = new FinalViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(_adapter);
        viewPager.setCurrentItem(1);
        bottomNavigationBar.setItemSelected(R.id.timer_bottom_nav, true);
    }

    @Override
    public void hideBottomNav() {
        bottomNavigationBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showBottomNav() {
        bottomNavigationBar.setVisibility(View.VISIBLE);
    }
}