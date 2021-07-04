package org.timerrubikscube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yashovardhan99.timeit.Timer;

import static com.yashovardhan99.timeit.Timer.*;

public class DemoActivity extends AppCompatActivity {

    Timer timer = new Timer(15000);
    Button button;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        button = findViewById(R.id.button);
        tv = findViewById(R.id.textView);
        button.setOnClickListener(v -> {
            timer.start();
            timer.setTextView(tv);

        });
        timer.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(Timer timer) {
                Log.d("timer3", "onTick: " + timer.getRemainingTime());
            }

            @Override
            public void onComplete(Timer timer) {
                Log.d("timer3", "onComplete: sesh");
            }
        });
    }

}