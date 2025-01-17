package com.example.sleepapp.SleepDiary.Tips;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepapp.R;

import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private List<String> tips;

    public TipAdapter(List<String> tips) {
        this.tips = tips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tipTextView.setText(tips.get(position));
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tipTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipTextView = itemView.findViewById(R.id.tipTextView);
        }
    }
}
