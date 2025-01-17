package com.example.sleepapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sleepapp.CalcSleepTime.CalcSleepPhasesFragment;
import com.example.sleepapp.JournalDatabase.JournalDay;
import com.example.sleepapp.JournalDatabase.SleepDiaryDataBase;
import com.example.sleepapp.MusicForSleep.RelaxMusicFragment;
import com.example.sleepapp.SleepJournaling.SleepJournalingActivity;
import com.example.sleepapp.SleepDiary.SleepDiaryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SleepDiaryDataBase dbHelper = new SleepDiaryDataBase(this);
        List<JournalDay> journalDayList = dbHelper.getAllJournalDays();
        dbHelper.close();

        if (journalDayList.isEmpty()) {
            startSecondActivityWithAnimation();
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
                startSecondActivityWithAnimation();
            }
        }

        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addFragment(new SleepDiaryFragment(), "Качество");
/*        viewPagerAdapter.addFragment(new RelaxMusicFragment(), "Музыка");
        viewPagerAdapter.addFragment(new CalcSleepPhasesFragment(), "Рассчёт фаз");*/

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(viewPagerAdapter.titles.get(position))
        ).attach();
        tabLayout.setTabMode(TabLayout.MODE_AUTO);
    }

    private void startSecondActivityWithAnimation() {
        Intent intent = new Intent(MainActivity.this, SleepJournalingActivity.class);
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
        startActivity(intent, bundle);
    }

    public static class ViewPagerAdapter extends FragmentStateAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }
    }
}

