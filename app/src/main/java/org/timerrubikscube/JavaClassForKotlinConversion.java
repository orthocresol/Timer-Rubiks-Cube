package org.timerrubikscube;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yashovardhan99.timeit.Timer;

import org.timerrubikscube.nonactivityclass.Item;

import java.util.ArrayList;
import java.util.Collections;

public class JavaClassForKotlinConversion {
    Button b = null;
    Timer timer;
    public void array(){
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
                if(timer.getRemainingTime() == 8000){
                    Log.d("timer", "onTick: 8s ase baal");
                }
                if(timer.getRemainingTime() == 3000){
                    Log.d("timer", "onTick: 3s ase baal");
                }
            }

            @Override
            public void onComplete(Timer timer) {

            }
        });
    }
}
