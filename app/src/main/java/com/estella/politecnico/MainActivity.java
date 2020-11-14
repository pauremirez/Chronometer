package com.estella.politecnico;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.estella.politecnico.utils.TimerUtil;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants: max time to count --> 10 hours
    private final long MAX_TIME_TO_COUNT_IN_MILLIS = TimeUnit.HOURS.toMillis(10);

    // Views
    private Button bntStart;
    private TextView textViewTime;
    private CountDownTimer countDownTimer;

    private TimerUtil timerUtil = new TimerUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set Button
        bntStart = findViewById(R.id.buttonStartCount);
        bntStart.setOnClickListener(this);
        // set TextView
        textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setText(timerUtil.getTimeStyleTextFromMillis(0));

        setViewsDependingTheState(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the countdown.
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == bntStart) {
            if (countDownTimer == null) {
                startTimer();
            } else {
                stopTimer();
            }
        }
    }

    private void startTimer() {
        setViewsDependingTheState(true);
        // this code is just to check some debugging tools from Android Studio. Please see e)
        // paragraph of exercise 1.
        boolean valueToBeModifiedUsingWatches = false;
        Toast.makeText(this, R.string.activity_main_toast_start_timer, Toast.LENGTH_SHORT).show();

        countDownTimer = new CountDownTimer(MAX_TIME_TO_COUNT_IN_MILLIS, 10) {

            // callback fired on regular interval.
            public void onTick(long millisUntilFinished) {
                long timeToBeConverted = MAX_TIME_TO_COUNT_IN_MILLIS - millisUntilFinished;
                textViewTime.setText(timerUtil.getTimeStyleTextFromMillis(timeToBeConverted));
            }

            // callback fired when the time is up.
            public void onFinish() {
                Toast.makeText(getApplicationContext(), R.string.activity_main_toast_finish_timer, Toast.LENGTH_SHORT)
                        .show();
                setViewsDependingTheState(false);
                countDownTimer = null;
            }
            // start the countdown.
        }.start();
    }

    private void stopTimer() {
        Toast.makeText(this, R.string.activity_main_toast_stop_timer, Toast.LENGTH_SHORT).show();
        setViewsDependingTheState(false);
        // Cancel the countdown.
        countDownTimer.cancel();
        countDownTimer = null;
    }

    private void setViewsDependingTheState(boolean isCounting) {
        Drawable icon;
        if (isCounting) {
            bntStart.setText(R.string.activity_main_button_stop);
            // get play icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_pause);
        } else {
            bntStart.setText(R.string.activity_main_button_start);
            // get pause icon from android resource
            icon = ContextCompat.getDrawable(this, android.R.drawable.ic_media_play);
        }
        // add an icon in left part of the button
        bntStart.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
    }
}
