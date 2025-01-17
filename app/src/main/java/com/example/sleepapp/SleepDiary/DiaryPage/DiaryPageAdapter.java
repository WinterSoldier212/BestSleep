package com.example.sleepapp.SleepDiary.DiaryPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepapp.JournalDatabase.JournalDay;
import com.example.sleepapp.R;

import java.util.List;

public class DiaryPageAdapter extends RecyclerView.Adapter<DiaryPageAdapter.JournalDayViewHolder> {
    private List<JournalDay> journalDays;
    private OnSaveEditPageDayClickListener listener;

    public interface OnSaveEditPageDayClickListener {
        void onSaveEditPageDayClick(String editText, JournalDay journalDay);
    }

    public DiaryPageAdapter(List<JournalDay> journalDays, OnSaveEditPageDayClickListener listener) {
        this.journalDays = journalDays;
        this.listener = listener;
    }

    @NonNull
    @Override
    public JournalDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal_day, parent, false);
        return new JournalDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalDayViewHolder holder, int position) {
        JournalDay journalDay = journalDays.get(position);
        holder.mDayNumberTextView.setText((position + 1) + ".");
        holder.mDateTextView.setText(journalDay.getDate());
        holder.mSleepQualityTextView.setText(journalDay.getSleepQuality() + "%");
        holder.mDiaryPageTextView.setText(journalDay.getDiaryPage());

        holder.mSaveEditDiaryPage.setOnClickListener(v -> listener.onSaveEditPageDayClick(
                holder.mDiaryPageTextView.getText().toString(),
                journalDay
        ));
    }

    @Override
    public int getItemCount() {
        return journalDays.size();
    }

    static class JournalDayViewHolder extends RecyclerView.ViewHolder {
        TextView mDayNumberTextView;
        TextView mDateTextView;
        TextView mSleepQualityTextView;
        EditText mDiaryPageTextView;
        CardView mSaveEditDiaryPage;

        public JournalDayViewHolder(@NonNull View itemView) {
            super(itemView);
            mDayNumberTextView = itemView.findViewById(R.id.dayNumberTextView);
            mDateTextView = itemView.findViewById(R.id.dateTextView);
            mSleepQualityTextView = itemView.findViewById(R.id.sleepQualityTextView);
            mDiaryPageTextView = itemView.findViewById(R.id.diaryPageTextView);
            mSaveEditDiaryPage = itemView.findViewById(R.id.save_edit_diary_page);
        }
    }
}
