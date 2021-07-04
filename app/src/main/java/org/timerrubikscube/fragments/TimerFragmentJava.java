package org.timerrubikscube.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.yashovardhan99.timeit.Stopwatch;
import com.yashovardhan99.timeit.Timer;

import org.timerrubikscube.R;
import org.timerrubikscube.nonactivityclass.ScrambleGenerator;


public class TimerFragmentJava extends Fragment {
    TextView scramble;
    ImageButton nextScrambleButton;
    TextView timeTv;
    AppCompatButton goBtn;
    RelativeLayout layout;
    Stopwatch stopwatch;
    Timer timer = new Timer(15000);
    Context _context;
    SwitchMaterial sw_inspection;
    Boolean isInspectionOn = true;
    Boolean isRunning = false;
    Boolean isInspecting = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        initVariable(view);
        clickListeners();
        return view;
    }

    private void clickListeners() {
        nextScrambleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scramble.setText(new ScrambleGenerator().giveScramble());
            }
        });
        goBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (isInspectionOn && !isInspecting) {
                    // nothing here
                } else
                    disappearElements();

                return false;
            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInspectionOn && !isInspecting) {
                    isInspecting = true;
                    //timer = new Timer(15000);
                    timer.setTextView(timeTv);
                    timer.start();
                    timeTv.setTextColor(ContextCompat.getColor(_context, R.color.red));

                } else if (!stopwatch.isStarted() && isRunning) {
                    if (isInspectionOn && timer.getRemainingTime() > 0) timer.stop();
                    stopwatch.start();
                    timeTv.setTextColor(ContextCompat.getColor(_context, R.color.black));
                }
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopwatch.isStarted()) {
                    stopwatch.stop();

                    reappearElements();
                }
            }
        });

        sw_inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInspectionOn = sw_inspection.isChecked();
            }
        });
        timer.setOnTickListener(new Timer.OnTickListener() {
            @Override
            public void onTick(Timer timer) {
                long remTime = timer.getRemainingTime();
                Log.d("timer4", "onTick: 809s");
                if(remTime > 7500 && remTime < 8500){
                    Log.d("timer4", "onTick: 8s");
                }

                if(remTime > 3000 && remTime < 3500){
                    Log.d("timer4", "onTick: 3s");
                }
            }

            @Override
            public void onComplete(Timer timer) {
                Log.d("timer4", "onComplete: dnf");
            }
        });
    }

    private void reappearElements() {
        isRunning = false;
        if (isInspectionOn) isInspecting = false;
        scramble.setText(new ScrambleGenerator().giveScramble());
        scramble.setVisibility(View.VISIBLE);
        goBtn.setVisibility(View.VISIBLE);
        nextScrambleButton.setVisibility( View.VISIBLE);
        layout.setBackgroundColor(ContextCompat.getColor(_context, R.color.white));
        timeTv.setTextSize(70F);
    }

    private void disappearElements() {
        isRunning = true;
        scramble.setVisibility(View.INVISIBLE);
        goBtn.setVisibility(View.INVISIBLE);
        nextScrambleButton.setVisibility(View.INVISIBLE);
        layout.setBackgroundColor(ContextCompat.getColor(_context, R.color.green_300));
        timeTv.setTextSize(100F);
        if(!isInspectionOn) timeTv.setText("00.00");
    }

    private void initVariable(View view) {
        scramble = view.findViewById(R.id.timer_scramble_tv);
        scramble.setText( new ScrambleGenerator().giveScramble());
        nextScrambleButton = view.findViewById(R.id.timer_next);
        timeTv = view.findViewById(R.id.timer_time);
        goBtn = view.findViewById(R.id.timer_go);
        layout = view.findViewById(R.id.timer_layout);
        stopwatch = new Stopwatch();
        stopwatch.setTextView(timeTv);
        timer.setTextView(timeTv);
        sw_inspection = view.findViewById(R.id.timer_enable_inspection);
        _context = requireContext();
    }
}