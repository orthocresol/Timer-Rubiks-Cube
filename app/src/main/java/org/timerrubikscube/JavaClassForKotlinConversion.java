package org.timerrubikscube;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.yashovardhan99.timeit.Timer;

import org.jetbrains.annotations.NotNull;
import org.timerrubikscube.finaldesign.fragments.FinalTimerFragment;

import java.util.ArrayList;
import java.util.Collections;

public class JavaClassForKotlinConversion {



    FinalTimerFragment.FragmentTimerListener listener;
    Button b = null;
    Timer timer;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    ChipNavigationBar bottomNavigationBar;

    public void array() {
        bottomNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.timer_bottom_nav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.stat_bottom_nav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.record_bottom_nav:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.timer_bottom_nav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.stat_bottom_nav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.record_bottom_nav:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });


        float result = 10.3434f;
        String toShow = String.format("%.2f", result);
        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        timer.setOnTickListener(new Timer.OnTickListener() {
            @Override
            public void onTick(Timer timer) {
                if (timer.getRemainingTime() == 8000) {
                    Log.d("timer", "onTick: 8s ase baal");
                }
                if (timer.getRemainingTime() == 3000) {
                    Log.d("timer", "onTick: 3s ase baal");
                }
            }

            @Override
            public void onComplete(Timer timer) {

            }
        });
    }
}
