package com.example.sleepapp.JournalDatabase;

import android.util.Log;

public class JournalDay {
    private int day;
    private String date;
    private int sleepQuality;
    private String diaryPage;

    public JournalDay() {
    }

    public JournalDay(String date, int sleepQuality, String diaryPage) {
        this.date = date;
        this.sleepQuality = sleepQuality;
        this.diaryPage = diaryPage;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        if (sleepQuality >= 0 && sleepQuality <= 100) {
            this.sleepQuality = sleepQuality;
        }else{
            Log.e("JournalDay_sasdasdads", "Invalid sleepQuality value: " + sleepQuality);
            this.sleepQuality = 0; // or throw exception
        }
    }

    public String getDiaryPage() {
        return diaryPage;
    }

    public void setDiaryPage(String diaryPage) {
        this.diaryPage = diaryPage;
    }

    @Override
    public String toString() {
        return "JournalDay{" +
                "day=" + day +
                ", date='" + date + '\'' +
                ", sleepQuality=" + sleepQuality +
                ", diaryPage='" + diaryPage + '\'' +
                '}';
    }
}
