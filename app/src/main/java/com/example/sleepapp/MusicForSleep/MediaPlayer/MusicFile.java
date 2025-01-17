package com.example.sleepapp.MusicForSleep.MediaPlayer;

public class MusicFile {
    private String title;
    private int resId;
    private boolean isPlaying;

    public MusicFile(String title, int resId) {
        this.title = title;
        this.resId = resId;
        this.isPlaying = false;
    }

    public MusicFile() {

    }

    public String getTitle() {
        return title;
    }

    public int getResId() {
        return resId;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
