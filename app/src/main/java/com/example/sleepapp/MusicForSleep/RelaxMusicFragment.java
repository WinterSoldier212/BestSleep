package com.example.sleepapp.MusicForSleep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicListManager;
import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicPlayer;
import com.example.sleepapp.MusicForSleep.MusicSelection.MusicSelectionActivity;
import com.example.sleepapp.MusicForSleep.TimePickerDialog.CustomTimePickerDialog;
import com.example.sleepapp.MusicForSleep.Timer.Timer;
import com.example.sleepapp.MusicForSleep.Timer.TimerListener;
import com.example.sleepapp.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class RelaxMusicFragment extends Fragment implements TimerListener {
    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCELED = 0;
    public static final int SELECTED_MUSIC = 1;

    private MusicPlayer musicPlayer;
    private UIController uiController;
    private Timer timer;
    private ImageView countdownTimer;
    private CircularProgressIndicator progressBar;
    private TextView shutdownTime;
    private Button stopTimerButton;

    public RelaxMusicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_for_sleep, container, false);
        init(view);
        return view;
    }


    private void init(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        shutdownTime = view.findViewById(R.id.shutdown_time_text_view);
        countdownTimer = view.findViewById(R.id.id_timer);
        musicPlayer = new MusicPlayer(getContext(),  MusicListManager.initMusicFiles());
        stopTimerButton = view.findViewById(R.id.stop_timer_button);
        timer = new Timer(progressBar, shutdownTime, stopTimerButton, this);
        uiController = new UIController(view, musicPlayer, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MusicSelectionActivity.class);
                startActivityForResult(intent, SELECTED_MUSIC);
            }
        });
        musicPlayer.loadMusic(0);
        uiController.updateMusicTitle(
                musicPlayer.getCurrentPosition()
        );
        countdownTimer.setOnClickListener(v -> showTimePickerDialog());
    }

    private void stopTimer() {
        timer.stopProgressBarTimer();
    }

    private void showTimePickerDialog() {
        CustomTimePickerDialog dialog = new CustomTimePickerDialog(
                getContext(),
                0,
                0,
                0,
                new CustomTimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(int hour, int minute, int second) {
                        timer.stopProgressBarTimer();
                        timer.startProgressBarTimer(hour, minute, second);
                    }
                }
        );
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if(musicPlayer != null) musicPlayer.release();
        super.onDestroy();
    }

    @Override
    public void onTimerFinished() {
        if(musicPlayer != null && musicPlayer.isPlaying()){
            musicPlayer.setMusicState(false);
            uiController.updatePlayPause();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == SELECTED_MUSIC) {
                int position = data.getIntExtra("position", -1);
                if (position == -1) {
                    return;
                }
                boolean isPlaying = musicPlayer.isPlaying();
                musicPlayer.loadMusic(position);
                uiController.updateMusicTitle(
                        musicPlayer.getCurrentPosition()
                );
                musicPlayer.setMusicState(isPlaying);
                uiController.updatePlayPause();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.i("onActivityResult", "Note operation canceled");
        } else {
            Log.w("onActivityResult", "Unhandled result code: " + resultCode);
        }
    }
}
