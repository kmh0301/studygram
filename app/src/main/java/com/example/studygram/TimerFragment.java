package com.example.studygram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class TimerFragment extends Fragment {
    public static long START_TIME_IN_MILLTS =1800000 ;
    private TextView mTextViewTimer;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonSet;
    private ProgressBar mProgressBar;
    private EditText mTimeMinutes;
    private EditText mTimeSeconds;

    private int progressstatus = 0;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLTS;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextViewTimer = view.findViewById(R.id.tv_timer);
        mButtonStartPause = view.findViewById(R.id.btn_start_pause);
        mButtonReset = view.findViewById(R.id.btn_reset);
        mProgressBar = view.findViewById(R.id.progress_circular);
        mTimeMinutes = view.findViewById(R.id.et_timer_m);
        mTimeSeconds = view.findViewById(R.id.et_timer_s);
        mButtonSet = view.findViewById(R.id.btn_set);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minutes = Integer.parseInt(mTimeMinutes.getText().toString());
                int seconds = Integer.parseInt(mTimeSeconds.getText().toString());

                START_TIME_IN_MILLTS = (long)(minutes*60*1000+seconds*1000);
                resetTimer(view);
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(mTimerRunning){
                    pauseTimer(view);
                }else{
                    startTimer(view);
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer(view);
            }
        });

        updateCountDownText();

    }

    private void startTimer(View view) {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {

            @Override
            public void onTick(long millisUnitFinished) {
                mTimeLeftInMillis = millisUnitFinished;
                updateCountDownText();
                progressstatus++;
                mProgressBar.setProgress((int) (progressstatus*100/(START_TIME_IN_MILLTS/1000)));

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(view.INVISIBLE);
                mButtonReset.setVisibility(view.VISIBLE);
                mProgressBar.setProgress(100);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("Pause");
        mButtonReset.setVisibility(view.INVISIBLE);
    }
    private void pauseTimer(View view) {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(view.VISIBLE);

    }
    private void resetTimer(View view) {
        mTimeLeftInMillis = START_TIME_IN_MILLTS;
        updateCountDownText();
        mButtonReset.setVisibility(view.INVISIBLE);
        mButtonStartPause.setVisibility(view.VISIBLE);
        progressstatus = 0;
        mProgressBar.setProgress(0);
    }
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis/1000) / 60;
        int seconds = (int) (mTimeLeftInMillis/1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewTimer.setText(timeLeftFormatted);
    }




}