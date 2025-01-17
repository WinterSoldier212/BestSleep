
package com.example.sleepapp.SleepJournaling;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sleepapp.R;
import com.example.sleepapp.JournalDatabase.JournalDay;
import com.example.sleepapp.JournalDatabase.SleepDiaryDataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SleepJournalingActivity extends AppCompatActivity {
    private int mNighttimeSleepDurationMinutes = -1;
    private int mSleepQuality = -1;
    private int mNumberOfNightWakingsPercentage = -1;
    private int mAlcoholConsumptionPercentage = -1;
    private int mCaffeineConsumptionPercentage = -1;
    private int mPhysicalActivityPercentage = -1;
    private int mEmotionalStatePercentage = -1;
    private int mBedroomComfortPercentage = -1;
    private String diary_page;

    private final List<CardView> cardViews = new ArrayList<>();
    private int originalCardBackgroundColor;

    public SleepJournalingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nighttime_sleep_duration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupNighttimeSleepDurationView();
    }

    private void setupNighttimeSleepDurationView() {
        setContentView(R.layout.activity_nighttime_sleep_duration);

        TimePicker timePickerBedtime = findViewById(R.id.timePickerSleep);
        TimePicker timePickerWakeUp = findViewById(R.id.timePickerWakeUp);
        Button continueButton = findViewById(R.id.next_sleep_quality_button);

        timePickerBedtime.setIs24HourView(true);
        timePickerWakeUp.setIs24HourView(true);

        // Set default time for bedtime to 21:00
        Calendar bedtime = Calendar.getInstance();
        bedtime.set(Calendar.HOUR_OF_DAY, 0);
        bedtime.set(Calendar.MINUTE, 0);
        timePickerBedtime.setHour(bedtime.get(Calendar.HOUR_OF_DAY));
        timePickerBedtime.setMinute(bedtime.get(Calendar.MINUTE));
        // Set default time for wake-up to 7:00
        Calendar wakeUp = Calendar.getInstance();
        wakeUp.set(Calendar.HOUR_OF_DAY, 0);
        wakeUp.set(Calendar.MINUTE, 0);
        timePickerWakeUp.setHour(wakeUp.get(Calendar.HOUR_OF_DAY));
        timePickerWakeUp.setMinute(wakeUp.get(Calendar.MINUTE));

        TextView skipButton = findViewById(R.id.skip_nighttime_sleep_duration);
        skipButton.setOnClickListener(v -> {finish();});

        continueButton.setOnClickListener(v -> {
            int bedtimeHour = timePickerBedtime.getHour();
            int bedtimeMinute = timePickerBedtime.getMinute();
            int wakeUpHour = timePickerWakeUp.getHour();
            int wakeUpMinute = timePickerWakeUp.getMinute();

            if (wakeUpHour < bedtimeHour) wakeUpHour += 24;

            mNighttimeSleepDurationMinutes = (wakeUpHour - bedtimeHour) * 60
                    - (wakeUpMinute - bedtimeMinute);

            setupSleepQualityAssessmentView();
        });
    }

    private void setupSleepQualityAssessmentView() {
        setContentView(R.layout.activity_sleep_quality_assessment);

        CardView sleepQualityButton1 = findViewById(R.id.sleep_quality_button_1);
        CardView sleepQualityButton2 = findViewById(R.id.sleep_quality_button_2);
        CardView sleepQualityButton3 = findViewById(R.id.sleep_quality_button_3);
        CardView sleepQualityButton4 = findViewById(R.id.sleep_quality_button_4);
        CardView sleepQualityButton5 = findViewById(R.id.sleep_quality_button_5);

        setupCardViewListeners(sleepQualityButton1, 0);
        setupCardViewListeners(sleepQualityButton2, 25);
        setupCardViewListeners(sleepQualityButton3, 50);
        setupCardViewListeners(sleepQualityButton4, 75);
        setupCardViewListeners(sleepQualityButton5, 100);

        TextView skipButton = findViewById(R.id.skip_sleep_quality);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_count_night_wakings_button);
        nextButton.setOnClickListener(v -> {
            if (mSleepQuality == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupCountNightWakingsView();
        });
    }

    private void setupCountNightWakingsView() {
        setContentView(R.layout.activity_count_night_wakings);

        CardView countNightWakingsButton1 = findViewById(R.id.count_night_wakings_button_1);
        CardView countNightWakingsButton2 = findViewById(R.id.count_night_wakings_button_2);
        CardView countNightWakingsButton3 = findViewById(R.id.count_night_wakings_button_3);
        CardView countNightWakingsButton4 = findViewById(R.id.count_night_wakings_button_4);

        setupCardViewListeners(countNightWakingsButton1, 100);
        setupCardViewListeners(countNightWakingsButton2, 75);
        setupCardViewListeners(countNightWakingsButton3, 50);
        setupCardViewListeners(countNightWakingsButton4, 25);

        TextView skipButton = findViewById(R.id.skip_count_night_wakings);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_alcohol_consumption_button);
        nextButton.setOnClickListener(v -> {
            if (mNumberOfNightWakingsPercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupAlcoholConsumptionView();
        });
    }

    private void setupAlcoholConsumptionView() {
        setContentView(R.layout.activity_alcohol_consumption);

        CardView alcoholConsumptionButtonYes = findViewById(R.id.yes_alcohol_consumption_button);
        CardView alcoholConsumptionButtonNo = findViewById(R.id.no_alcohol_consumption_button);

        setupCardViewListeners(alcoholConsumptionButtonYes, 0);
        setupCardViewListeners(alcoholConsumptionButtonNo, 100);

        TextView skipButton = findViewById(R.id.skip_alcohol_consumption);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_caffeine_consumption_button);
        nextButton.setOnClickListener(v -> {
            if (mAlcoholConsumptionPercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupCaffeineConsumptionView();
        });
    }

    private void setupCaffeineConsumptionView() {
        setContentView(R.layout.activity_caffeine_consumption);

        CardView caffeineConsumptionButtonYes = findViewById(R.id.yes_caffeine_consumption_button);
        CardView caffeineConsumptionButtonNo = findViewById(R.id.no_caffeine_consumption_button);

        setupCardViewListeners(caffeineConsumptionButtonYes, 0);
        setupCardViewListeners(caffeineConsumptionButtonNo, 100);

        TextView skipButton = findViewById(R.id.skip_caffeine_consumption);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_physical_education_button);
        nextButton.setOnClickListener(v -> {
            if (mCaffeineConsumptionPercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupPhysicalActivityView();
        });
    }

    private void setupPhysicalActivityView() {
        setContentView(R.layout.activity_physical_education);

        CardView physicalActivityButtonYes = findViewById(R.id.yes_physical_education_button);
        CardView physicalActivityButtonNo = findViewById(R.id.no_physical_education_button);

        setupCardViewListeners(physicalActivityButtonYes, 0);
        setupCardViewListeners(physicalActivityButtonNo, 100);

        TextView skipButton = findViewById(R.id.skip_physical_education);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_emotional_state_button);
        nextButton.setOnClickListener(v -> {
            if (mPhysicalActivityPercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupEmotionalStateView();
        });
    }

    private void setupEmotionalStateView() {
        setContentView(R.layout.activity_emotional_state);

        CardView emotionalStateButton1 = findViewById(R.id.emotional_state_button_1);
        CardView emotionalStateButton2 = findViewById(R.id.emotional_state_button_2);
        CardView emotionalStateButton3 = findViewById(R.id.emotional_state_button_3);
        CardView emotionalStateButton4 = findViewById(R.id.emotional_state_button_4);
        CardView emotionalStateButton5 = findViewById(R.id.emotional_state_button_5);

        setupCardViewListeners(emotionalStateButton1, 0);
        setupCardViewListeners(emotionalStateButton2, 25);
        setupCardViewListeners(emotionalStateButton3, 50);
        setupCardViewListeners(emotionalStateButton4, 75);
        setupCardViewListeners(emotionalStateButton5, 100);

        TextView skipButton = findViewById(R.id.skip_emotional_state);
        skipButton.setOnClickListener(v -> {finish();});

        Button nextButton = findViewById(R.id.next_bedroom_comfort_button);
        nextButton.setOnClickListener(v -> {
            if (mEmotionalStatePercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupBedroomComfortView();
        });
    }

    private void setupBedroomComfortView() {
        setContentView(R.layout.activity_bedroom_comfort);

        CardView bedroomComfortButton1 = findViewById(R.id.bedroom_comfort_button_1);
        CardView bedroomComfortButton2 = findViewById(R.id.bedroom_comfort_button_2);
        CardView bedroomComfortButton3 = findViewById(R.id.bedroom_comfort_button_3);
        CardView bedroomComfortButton4 = findViewById(R.id.bedroom_comfort_button_4);
        CardView bedroomComfortButton5 = findViewById(R.id.bedroom_comfort_button_5);

        setupCardViewListeners(bedroomComfortButton1, 0);
        setupCardViewListeners(bedroomComfortButton2, 25);
        setupCardViewListeners(bedroomComfortButton3, 50);
        setupCardViewListeners(bedroomComfortButton4, 75);
        setupCardViewListeners(bedroomComfortButton5, 100);

        TextView skipButton = findViewById(R.id.skip_bedroom_comfort);
        skipButton.setOnClickListener(v -> {finish();});

        Button finishButton = findViewById(R.id.next_sleep_diary_page_button);
        finishButton.setOnClickListener(v -> {
            if (mBedroomComfortPercentage == -1) {
                Toast.makeText(getApplication(), "Выберите одну из кнопок!", Toast.LENGTH_SHORT).show();
                return;
            }
            setupCompletionSleepDiaryView();
        });
    }

    private void setupCompletionSleepDiaryView() {
        setContentView(R.layout.activity_page_sleep_diary);

        CardView finishButton = findViewById(R.id.finish_filling_statistic_button);
        finishButton.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.edit_diary_page);

            diary_page = editText.getText().toString();
            addStatisticDayInDatabase();
            finish();
        });
    }

    private void setupCardViewListeners(CardView cardView, int value) {
        cardViews.add(cardView);
        originalCardBackgroundColor = cardView.getCardBackgroundColor().getDefaultColor();
        cardView.setOnClickListener(v -> {
            handleCardClick(cardView, value);
        });
    }

    private void handleCardClick(CardView clickedCard, int value) {
        resetCardViewColors();
        clickedCard.setCardBackgroundColor(getColor(R.color.special_purple));

        if (clickedCard.getId() == R.id.sleep_quality_button_1 || clickedCard.getId() == R.id.sleep_quality_button_2 ||
                clickedCard.getId() == R.id.sleep_quality_button_3 || clickedCard.getId() == R.id.sleep_quality_button_4 ||
                clickedCard.getId() == R.id.sleep_quality_button_5) {
            mSleepQuality = value;
        } else if (clickedCard.getId() == R.id.count_night_wakings_button_1 || clickedCard.getId() == R.id.count_night_wakings_button_2 ||
                clickedCard.getId() == R.id.count_night_wakings_button_3 || clickedCard.getId() == R.id.count_night_wakings_button_4) {
            mNumberOfNightWakingsPercentage = value;
        } else if (clickedCard.getId() == R.id.yes_alcohol_consumption_button || clickedCard.getId() == R.id.no_alcohol_consumption_button) {
            mAlcoholConsumptionPercentage = value;
        } else if (clickedCard.getId() == R.id.yes_caffeine_consumption_button || clickedCard.getId() == R.id.no_caffeine_consumption_button) {
            mCaffeineConsumptionPercentage = value;
        } else if (clickedCard.getId() == R.id.yes_physical_education_button || clickedCard.getId() == R.id.no_physical_education_button) {
            mPhysicalActivityPercentage = value;
        } else if (clickedCard.getId() == R.id.emotional_state_button_1 || clickedCard.getId() == R.id.emotional_state_button_2 ||
                clickedCard.getId() == R.id.emotional_state_button_3 || clickedCard.getId() == R.id.emotional_state_button_4 ||
                clickedCard.getId() == R.id.emotional_state_button_5) {
            mEmotionalStatePercentage = value;
        } else if (clickedCard.getId() == R.id.bedroom_comfort_button_1 || clickedCard.getId() == R.id.bedroom_comfort_button_2 ||
                clickedCard.getId() == R.id.bedroom_comfort_button_3 || clickedCard.getId() == R.id.bedroom_comfort_button_4 ||
                clickedCard.getId() == R.id.bedroom_comfort_button_5) {
            mBedroomComfortPercentage = value;
        }
    }

    private void resetCardViewColors() {
        for (CardView cardView : cardViews) {
            cardView.setCardBackgroundColor(originalCardBackgroundColor);
        }
    }

    public int calculateSleepQualityScore() {
        final double timeSleepWeight = 2;
        final double sleepQualityWeight = 0.25;
        final double nightWakingsWeight = 0.15;
        final double alcoholWeight = 0.1;
        final double caffeineWeight = 0.05;
        final double physicalActivityWeight = 0.05;
        final double emotionalStateWeight = 0.1;
        final double bedroomComfortWeight = 0.1;

        mNighttimeSleepDurationMinutes /= 60;

        mNighttimeSleepDurationMinutes = Math.min(mNighttimeSleepDurationMinutes, 10);

        // Расчет итогового балла
        double totalScore = (mNighttimeSleepDurationMinutes * timeSleepWeight) +
                (mSleepQuality * sleepQualityWeight) +
                (mNumberOfNightWakingsPercentage * nightWakingsWeight) +
                (mAlcoholConsumptionPercentage * alcoholWeight) +
                (mCaffeineConsumptionPercentage * caffeineWeight) +
                (mPhysicalActivityPercentage * physicalActivityWeight) +
                (mEmotionalStatePercentage * emotionalStateWeight) +
                (mBedroomComfortPercentage * bedroomComfortWeight);

        return (int)totalScore;
    }

    private void addStatisticDayInDatabase() {
        int quality_sleep = calculateSleepQualityScore();

        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String current_data = ft.format(new Date());

        JournalDay journalDay = new JournalDay(current_data, quality_sleep, diary_page);

        SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(this);
        dbHelper.addJournalDay(journalDay);
    }
}
