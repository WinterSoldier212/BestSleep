
package com.example.sleepapp.JournalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SleepDiaryDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sleep_diary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SLEEP_DIARY = "sleep_diary";

    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_SLEEP_QUALITY = "sleep_quality";
    private static final String COLUMN_DIARY_PAGE = "diary_page";

    public SleepDiaryDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " +
                TABLE_SLEEP_DIARY + " (" +
                COLUMN_DAY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_SLEEP_QUALITY + " INTEGER CHECK(" +
                COLUMN_SLEEP_QUALITY + " >= 0 AND " +
                COLUMN_SLEEP_QUALITY + " <= 100), " +
                COLUMN_DIARY_PAGE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLEEP_DIARY);
        onCreate(db);
    }

    public  void reload() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLEEP_DIARY);
        db.close();
    }

    public long addJournalDay(JournalDay journalDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, journalDay.getDate());
        values.put(COLUMN_SLEEP_QUALITY, journalDay.getSleepQuality());
        values.put(COLUMN_DIARY_PAGE, journalDay.getDiaryPage());

        long day = db.insert(TABLE_SLEEP_DIARY, null, values);
        db.close();
        return day;
    }

    public JournalDay getJournalDay(int dayId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SLEEP_DIARY, new String[]{COLUMN_DAY,
                        COLUMN_DATE,
                        COLUMN_SLEEP_QUALITY,
                        COLUMN_DIARY_PAGE
                }, COLUMN_DAY + "=?",
                new String[]{String.valueOf(dayId)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        JournalDay journalDay = new JournalDay(
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3)
        );
        journalDay.setDay(Integer.parseInt(cursor.getString(0)));
        cursor.close();
        db.close();
        return journalDay;
    }

    public JournalDay getLastJournalDay() {
        SQLiteDatabase db = this.getReadableDatabase();
        JournalDay journalDay = null; // Инициализируем null

        Cursor cursor = db.query(
                TABLE_SLEEP_DIARY,
                new String[]{COLUMN_DAY, COLUMN_DATE, COLUMN_SLEEP_QUALITY, COLUMN_DIARY_PAGE},
                null, // No WHERE clause, we want all rows
                null,
                null,
                null,
                COLUMN_DAY + " DESC", // Order by day_id descending
                "1" // Limit to 1 row
        );

        if (cursor != null && cursor.moveToFirst()) {
            journalDay = new JournalDay(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SLEEP_QUALITY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIARY_PAGE))
            );
            journalDay.setDay(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DAY)));
            cursor.close();
        }

        db.close();
        return journalDay;
    }

    public List<JournalDay> getAllJournalDays() {
        List<JournalDay> journalDaysList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SLEEP_DIARY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                JournalDay journalDay = new JournalDay();
                journalDay.setDay(Integer.parseInt(cursor.getString(0)));
                journalDay.setDate(cursor.getString(1));
                journalDay.setSleepQuality(Integer.parseInt(cursor.getString(2)));
                journalDay.setDiaryPage(cursor.getString(3));

                journalDaysList.add(journalDay);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return journalDaysList;
    }

    public int updateJournalDay(JournalDay journalDay) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, journalDay.getDate());
        values.put(COLUMN_SLEEP_QUALITY, journalDay.getSleepQuality());
        values.put(COLUMN_DIARY_PAGE, journalDay.getDiaryPage());

        int rowsAffected = db.update(TABLE_SLEEP_DIARY, values, COLUMN_DAY + "=?",
                new String[]{String.valueOf(journalDay.getDay())});
        db.close();
        return rowsAffected;
    }

    public void deleteJournalDay(int dayId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SLEEP_DIARY, COLUMN_DAY + "=?", new String[]{String.valueOf(dayId)});
        db.close();
    }

}

