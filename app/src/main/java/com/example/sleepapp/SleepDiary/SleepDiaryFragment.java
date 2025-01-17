package com.example.sleepapp.SleepDiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.sleepapp.JournalDatabase.JournalDay;
import com.example.sleepapp.R;
import com.example.sleepapp.JournalDatabase.SleepDiaryDataBase;
import com.example.sleepapp.SleepJournaling.SleepJournalingActivity;
import com.example.sleepapp.SleepDiary.DiaryPage.DiaryPageActivity;
import com.example.sleepapp.SleepDiary.Tips.TipsActivity;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SleepDiaryFragment extends Fragment {
    TextView mTextPercentSleep;
    TextView mDayStatistic;
    TextView mDiaryPage;
    TextView mTextSleepDesc;
    CircularProgressIndicator mCircularProgressIndicator;
    CardView mButtonSleepRegime;
    CardView mButtonSleepNutrition;
    CardView mButtonSleepActivity;
    CardView mButtonSleepEnvironment;
    CardView mButtonSleepPsycho;
    CardView mCardDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_diary, container, false);

        mTextPercentSleep = view.findViewById(R.id.text_percent_sleep);
        mDayStatistic = view.findViewById(R.id.day_statistic);
        mCircularProgressIndicator = view.findViewById(R.id.statistic_sleep_quality);
        mTextSleepDesc = view.findViewById(R.id.text_sleep_desc);
        mDiaryPage = view.findViewById(R.id.diary_page);

        mCardDay = view.findViewById(R.id.card_day);

        mButtonSleepRegime = view.findViewById(R.id.button_sleep_regime);
        mButtonSleepNutrition = view.findViewById(R.id.button_sleep_nutrition);
        mButtonSleepActivity = view.findViewById(R.id.button_sleep_activity);
        mButtonSleepEnvironment = view.findViewById(R.id.button_sleep_environment);
        mButtonSleepPsycho = view.findViewById(R.id.button_sleep_psycho);

        mCircularProgressIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(getContext());
                List<JournalDay> journalDayList = dbHelper.getAllJournalDays();
                dbHelper.close();

                if (journalDayList.isEmpty()) {
                    Intent intent = new Intent(getActivity(), SleepJournalingActivity.class);
                    startActivity(intent);
                } else {
                    SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
                    String current_data = ft.format(new Date());

                    boolean dayIsExists = false;
                    for (JournalDay journalDay : journalDayList) {
                        String day_data = journalDay.getDate();

                        if (current_data.equals(day_data)) {
                            dayIsExists = true;
                            break;
                        }
                    }

                    if (!dayIsExists) {
                        Intent intent = new Intent(getActivity(), SleepJournalingActivity.class);
                        startActivity(intent);
                    } else {
                        mCircularProgressIndicator.setOnClickListener(view -> {});
                    }
                }
            }
        });

        mCardDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaryPageActivity.class);
                startActivity(intent);
            }
        });

        mButtonSleepRegime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsActivity.class);
                intent.putExtra("category", "Режим и гигиена сна");
                startActivity(intent);
            }
        });
        mButtonSleepNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsActivity.class);
                intent.putExtra("category", "Питание и напитки");
                startActivity(intent);
            }
        });
        mButtonSleepActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsActivity.class);
                intent.putExtra("category", "Активность и образ жизни");
                startActivity(intent);
            }
        });
        mButtonSleepEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsActivity.class);
                intent.putExtra("category", "Комфортная среда");
                startActivity(intent);
            }
        });
        mButtonSleepPsycho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsActivity.class);
                intent.putExtra("category", "Психологическая подготовка");
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(getContext());
        JournalDay lastJournalDay = dbHelper.getLastJournalDay();

        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        String current_data = ft.format(new Date());

        if (lastJournalDay != null) {
            if (!current_data.equals(lastJournalDay.getDate())) {
                mDiaryPage.setText("Заполните страницу дневника!");
                return;
            }

            mTextPercentSleep.setText(
                    lastJournalDay.getSleepQuality() + "%"
            );
            mDayStatistic.setText(
                    "День " + lastJournalDay.getDay()
            );
            mCircularProgressIndicator.setProgress(lastJournalDay.getSleepQuality());

            if (lastJournalDay.getSleepQuality() > 90) {
                mTextSleepDesc.setText("Отличный сон");
            }
            else if (lastJournalDay.getSleepQuality() > 75) {
                mTextSleepDesc.setText("Хороший сон");
            }
            else if (lastJournalDay.getSleepQuality() > 60) {
                mTextSleepDesc.setText("Средний сон");
            }
            else if (lastJournalDay.getSleepQuality() > 40) {
                mTextSleepDesc.setText("Плохой сон");
            } else {
                mTextSleepDesc.setText("Очень плохой сон");
            }

            if (!lastJournalDay.getDiaryPage().isEmpty()) {
                mDiaryPage.setText(lastJournalDay.getDiaryPage());
            } else {
                mDiaryPage.setText("Заполните страницу дневника!");
            }
        }
    }
}
