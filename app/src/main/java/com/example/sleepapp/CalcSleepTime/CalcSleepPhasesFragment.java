package com.example.sleepapp.CalcSleepTime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.sleepapp.R;

public class CalcSleepPhasesFragment extends Fragment {
    private CardView mCalcButton;
    private TimePicker mTimePicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc_sleep_phases, container, false);

        if (view == null) {
            Log.d("SLEEPAPP", "View == null -> CalcSleepActivity");
        }
        mCalcButton = view.findViewById(R.id.calcButton);
        mTimePicker = view.findViewById(R.id.timePicker);

        mTimePicker.setHour(6);
        mTimePicker.setMinute(30);

        mCalcButton.setOnClickListener(v -> {
            int minute = mTimePicker.getMinute();
            int hour = mTimePicker.getHour();

            Log.d("TIMEPICKER", hour + " - " + minute);
            Intent intent = new Intent(requireContext(), ResultCalcSleepPhasesActivity.class);
            intent.putExtra("hour", hour);
            intent.putExtra("minute", minute);
            startActivity(intent);
        });
        return view;
    }
}
