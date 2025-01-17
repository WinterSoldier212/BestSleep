package com.example.sleepapp.MusicForSleep.MediaPlayer;

public interface MediaPlayerInterface {
    void loadMusic(int position);
    void startMusic();
    void pauseMusic();
    void setMusicState(boolean isPlaying);
    int getCurrentPosition();
    boolean isPlaying();
    void release();
    void setNext();
    void setPrev();
}
