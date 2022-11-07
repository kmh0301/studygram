package com.example.studygram;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TimerActivity extends AppCompatActivity {

    TextView timerTextView;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timer);

        timerTextView = findViewById(R.id.timer);

        Button b = (Button) findViewById(R.id.btn_start_pause);
        b.setText("start");
        b.setOnClickListener(v -> {
            Button b1 = (Button) v;
            if (b1.getText().equals("stop")) {
                timerHandler.removeCallbacks(timerRunnable);
                b1.setText("start");
            } else {
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                b1.setText("stop");
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.btn_start_pause);
        b.setText("start");
    }

}