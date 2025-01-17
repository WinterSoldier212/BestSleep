package com.example.sleepapp.MusicForSleep;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicFile;
import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicPlayer;
import com.example.sleepapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UIController {
    private FloatingActionButton playPauseBtn;
    private ImageView nextBtn, prevBtn, musicVisible;
    private TextView mMusicTitle;
    private View view;
    private View.OnClickListener listener_selection_music;

    public static MusicPlayer musicPlayer;


    public UIController(View view, MusicPlayer musicPlayer, View.OnClickListener listener) {
        this.view = view;
        this.musicPlayer = musicPlayer;
        this.listener_selection_music = listener;
        initView();
    }

    private void initView() {
        mMusicTitle = view.findViewById(R.id.song_name);
        playPauseBtn = view.findViewById(R.id.play_pause);
        nextBtn = view.findViewById(R.id.id_next);
        prevBtn = view.findViewById(R.id.id_prev);
        musicVisible = view.findViewById(R.id.id_music_visible);

        setupButtons();
    }

    private void setupButtons() {
        playPauseBtn.setOnClickListener(v -> {
            togglePlayPause();
        });

        nextBtn.setOnClickListener(v -> {
            musicPlayer.setNext();
            updateMusicTitle(musicPlayer.getCurrentPosition());
        });

        prevBtn.setOnClickListener(v -> {
            musicPlayer.setPrev();
            updateMusicTitle(musicPlayer.getCurrentPosition());
        });

        musicVisible.setOnClickListener(listener_selection_music);
    }

    private void togglePlayPause() {
        musicPlayer.setMusicState(!musicPlayer.isPlaying());
        updatePlayPause();
    }

    public void updateMusicTitle(int position){
        mMusicTitle.setText(musicPlayer.getTitleCurrentMusic());
    }

    public void updatePlayPause() {
        if (musicPlayer.isPlaying()) {
            playPauseBtn.setImageResource(R.drawable.ic_pause);
        } else {
            playPauseBtn.setImageResource(R.drawable.ic_play);
        }
    }

}
