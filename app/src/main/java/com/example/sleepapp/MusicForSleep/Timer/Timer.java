package com.example.sleepapp.MusicForSleep.Timer;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Timer {
    private int totalTimeInSecond;
    private int currentProgress = 0;
    private Handler handler;
    private Runnable progressRunnable;
    private ProgressBar progressBar;
    private TextView shutdownTime;
    private TimerListener timerListener;
    private Button stopTimerButton;

    public Timer(ProgressBar progressBar, TextView shutdownTime, Button stopTimerButton, TimerListener timerListener) {
        this.progressBar = progressBar;
        this.shutdownTime = shutdownTime;
        this.timerListener = timerListener;
        this.stopTimerButton = stopTimerButton;

        this.stopTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopProgressBarTimer();
                progressBar.setProgress(progressBar.getMax());
            }
        });
    }

    public void startProgressBarTimer(int hour, int minutes, int second) {
        totalTimeInSecond = calculateTotalSeconds(hour, minutes, second);

        if (totalTimeInSecond <= 0) {
            handleZeroTime();
            return;
        }

        stopTimerButton.setVisibility(View.VISIBLE);
        prepareProgressBar();
        startTimer();
    }

    private int calculateTotalSeconds(int hour, int minutes, int second) {
        return hour * 3600 + minutes * 60 + second;
    }

    private void handleZeroTime() {
        progressBar.setMax(1);
        progressBar.setProgress(1);
        updateTimerText();
    }

    private void prepareProgressBar() {
        progressBar.setMax(totalTimeInSecond);
        progressBar.setProgress(0);
        currentProgress = 0;
    }

    private void startTimer() {
        handler = new Handler(Looper.getMainLooper());
        progressRunnable = new Runnable() {
            @Override
            public void run() {
                updateTimerText();
                progressBar.setProgress(currentProgress);

                if (currentProgress < totalTimeInSecond) {
                    currentProgress++;
                    handler.postDelayed(this, 1000);
                } else {
                    timerListener.onTimerFinished();
                    stopProgressBarTimer();
                }
            }
        };
        handler.post(progressRunnable);
    }

    private void updateTimerText() {
        int remainingSeconds = totalTimeInSecond - currentProgress;
        if(remainingSeconds < 0) remainingSeconds = 0;
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        shutdownTime.setText(time);
    }

    public void stopProgressBarTimer() {
        if (handler != null && progressRunnable != null) {
            handler.removeCallbacks(progressRunnable);
            handler = null;
            progressRunnable = null;
        }
        String time = String.valueOf("00:00:00");
        shutdownTime.setText(time);
        stopTimerButton.setVisibility(View.GONE);
    }

}
