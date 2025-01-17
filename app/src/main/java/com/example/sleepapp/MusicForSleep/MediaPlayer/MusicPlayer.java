package com.example.sleepapp.MusicForSleep.MediaPlayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.List;

public class MusicPlayer implements MediaPlayerInterface {
    private MediaPlayer mediaPlayer;
    private List<MusicFile> musicFiles;
    private int currentPosition = 0;
    private Context context;

    public MusicPlayer(Context context, List<MusicFile> musicFiles){
        this.context = context;
        this.musicFiles = musicFiles;
    }

    @Override
    public void loadMusic(int position) {
        if (position == currentPosition && mediaPlayer != null) return;

        musicFiles.get(currentPosition).setPlaying(false);
        musicFiles.get(position).setPlaying(true);

        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()) mediaPlayer.stop();
            mediaPlayer.release();
        }

        currentPosition = position;
        mediaPlayer = MediaPlayer.create(context, musicFiles.get(currentPosition).getResId());
        mediaPlayer.setLooping(true);
    }

    @Override
    public void startMusic() {
        if(mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void setMusicState(boolean isPlaying) {
        if(isPlaying){
            startMusic();
        }else {
            pauseMusic();
        }
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public boolean isPlaying(){
        if(mediaPlayer != null){
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    @Override
    public void release() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void setNext() {
        setMusicPlaying((currentPosition + 1) % musicFiles.size());
    }
    @Override
    public void setPrev(){
        setMusicPlaying(
                (currentPosition - 1) < 0 ? (musicFiles.size() - 1) : (currentPosition - 1)
        );
    }
    private void setMusicPlaying(int position) {
        boolean isPlaying = mediaPlayer.isPlaying();
        loadMusic(position);
        setMusicState(isPlaying);
    }

    public List<MusicFile> getMusicList(){ return musicFiles;}

    public String getTitleCurrentMusic() {
        return musicFiles.get(getCurrentPosition()).getTitle();
    }
}
