package com.example.sleepapp.MusicForSleep.TimePickerDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.sleepapp.R;


public class CustomTimePickerDialog extends Dialog {

    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private Button okButton;
    private Button cancelButton;

    private int initialHour;
    private int initialMinute;
    private int initialSecond;
    private OnTimeSetListener onTimeSetListener;

    public CustomTimePickerDialog(Context context, int initialHour, int initialMinute, int initialSecond, OnTimeSetListener onTimeSetListener) {
        super(context);
        this.initialHour = initialHour;
        this.initialMinute = initialMinute;
        this.initialSecond = initialSecond;
        this.onTimeSetListener = onTimeSetListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time_picker);

        hourPicker = findViewById(R.id.hourPicker);
        minutePicker = findViewById(R.id.minutePicker);
        secondPicker = findViewById(R.id.secondPicker);
        okButton = findViewById(R.id.okButton);
        cancelButton = findViewById(R.id.cancelButton);

        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(6);
        hourPicker.setValue(initialHour);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(initialMinute);

        secondPicker.setMinValue(0);
        secondPicker.setMaxValue(59);
        secondPicker.setValue(initialSecond);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTimeSetListener.onTimeSet(
                        hourPicker.getValue(),
                        minutePicker.getValue(),
                        secondPicker.getValue()
                );
                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    public interface OnTimeSetListener {
        void onTimeSet(int hour, int minute, int second);
    }
}
