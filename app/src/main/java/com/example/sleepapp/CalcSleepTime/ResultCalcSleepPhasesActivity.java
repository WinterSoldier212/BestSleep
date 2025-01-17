package com.example.sleepapp.CalcSleepTime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sleepapp.R;

public class ResultCalcSleepPhasesActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        setContentView(R.layout.activity_resul_calc_time_sleep);

        Intent intent = getIntent();
        int hour = intent.getIntExtra("hour", -1);
        int minute = intent.getIntExtra("minute", -1);
        if (hour == -1 || minute == -1) {
            Log.e("TimeCalculation", "Invalid hour or minute received from intent.");
            return;
        }

        int[] times = {
                R.id.sixthTimeSleep,
                R.id.fifthTimeSleep,
                R.id.fourthTimeSleep,
                R.id.thirdTimeSleep,
                R.id.secondTimeSleep,
                R.id.firstTimeSleep,
        };

        for (int i = 0; i < times.length; i++) {
            TextView textView = findViewById(times[i]);
            if (textView == null) {
                Log.e("TimeCalculation", "TextView with ID " + times[i] + " not found.");
                continue;
            }

            int timeOffsetMinutes = (i + 1) * 90;
            int totalMinutes = hour * 60 + minute - timeOffsetMinutes;
            int calculatedHour = (totalMinutes + 1440) / 60 % 24;
            int calculatedMinute = Math.abs(totalMinutes % 60);

            String time = String.format("%02d:%02d", calculatedHour, calculatedMinute);
            textView.setText(time);
        }


    }
}
