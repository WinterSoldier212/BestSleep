package com.example.sleepapp.MusicForSleep.MediaPlayer;

import com.example.sleepapp.R;

import java.util.ArrayList;
import java.util.List;

public class MusicListManager {
    public static List<MusicFile> initMusicFiles() {
        List<MusicFile> listSongs = new ArrayList<>();
        listSongs.add(new MusicFile("Космическая музыка", R.raw.music_1));
        listSongs.add(new MusicFile("Мурчание у костра", R.raw.music_2));
        listSongs.add(new MusicFile("Журчание речки", R.raw.music_3));
        listSongs.add(new MusicFile("Течение воды", R.raw.music_4));
        listSongs.add(new MusicFile("Стрекотание сверчков", R.raw.music_5));
        listSongs.add(new MusicFile("Расслабляющая музыка-1", R.raw.music_6));
        listSongs.add(new MusicFile("Расслабляющая музыка-2", R.raw.music_7));
        listSongs.add(new MusicFile("Расслабляющая музыка-3", R.raw.music_8));
        listSongs.add(new MusicFile("Музыка для глубокого сна", R.raw.music_12));
        listSongs.add(new MusicFile("Музыка для медитации", R.raw.music_13));
        listSongs.add(new MusicFile("Музыка звёзд", R.raw.music_15));
        listSongs.add(new MusicFile("Космический полёт", R.raw.music_16));

        return listSongs;
    }
}
