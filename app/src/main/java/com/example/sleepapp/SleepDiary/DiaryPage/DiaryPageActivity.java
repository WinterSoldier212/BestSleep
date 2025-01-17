package com.example.sleepapp.SleepDiary.DiaryPage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepapp.JournalDatabase.JournalDay;
import com.example.sleepapp.JournalDatabase.SleepDiaryDataBase;
import com.example.sleepapp.R;
import java.util.List;

public class DiaryPageActivity extends AppCompatActivity {
    CardView mCloseButton;
    RecyclerView mJournalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_day);

        SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(getApplicationContext());
        List<JournalDay> journalDayList = dbHelper.getAllJournalDays();
        dbHelper.close();

        mJournalRecyclerView = findViewById(R.id.journalDayRecyclerView);
        mCloseButton = findViewById(R.id.closeButton);
        mCloseButton.setOnClickListener(v -> finish());

        mJournalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DiaryPageAdapter adapter = new DiaryPageAdapter(
                journalDayList,
                new DiaryPageAdapter.OnSaveEditPageDayClickListener() {
                    @Override
                    public void onSaveEditPageDayClick(String editText, JournalDay journalDay) {
                        if (editText.isEmpty()) editText = "";

                        journalDay.setDiaryPage(editText);

                        SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(getApplicationContext());
                        dbHelper.updateJournalDay(journalDay);
                        dbHelper.close();
                    }
                }
        );

        mJournalRecyclerView.setAdapter(adapter);
    }
}






