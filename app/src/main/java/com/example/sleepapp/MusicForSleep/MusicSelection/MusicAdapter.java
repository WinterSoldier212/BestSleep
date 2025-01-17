package com.example.sleepapp.MusicForSleep.MusicSelection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicFile;
import com.example.sleepapp.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    private static RequestBuilder<Drawable> mGlide;

    private final Context mContext;
    private final List<MusicFile> mFiles;

    private OnMusicClickListener listener;

    public interface OnMusicClickListener {
        void onMusicClick(int position);
    }

    public MusicAdapter(Context mContext, List<MusicFile> mFiles, OnMusicClickListener listener)
    {
        this.mFiles = mFiles;
        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mTextView.setText(mFiles.get(position).getTitle());
        holder.mTextView.setOnClickListener(v -> listener.onMusicClick(position));

        if (mFiles.get(position).isPlaying()) {
            holder.mTextView.setBackgroundResource(R.drawable.gradient_bg_selected_music_item);
        } else {
            holder.mTextView.setBackgroundResource(R.drawable.gradient_bg_music_item);
        }
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.music_item_text);
        }
    }


}
