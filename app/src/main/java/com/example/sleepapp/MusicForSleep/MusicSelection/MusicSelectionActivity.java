package com.example.sleepapp.MusicForSleep.MusicSelection;

import static com.example.sleepapp.MusicForSleep.UIController.musicPlayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepapp.MusicForSleep.MediaPlayer.MusicFile;
import com.example.sleepapp.R;

import java.util.List;

public class MusicSelectionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MusicAdapter mMusicAdapter;
    private CardView closeButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        setContentView(R.layout.activity_music_selection);

        closeButton = findViewById(R.id.close_selection_music);
        mRecyclerView = findViewById(R.id.recycler_view_music);
        mRecyclerView.setHasFixedSize(true);

        setupRecyclerView();

        closeButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            setResult(1, resultIntent);
            finish();
        });

    }

    private void setupRecyclerView() {
        List<MusicFile> musicFiles = musicPlayer.getMusicList();

        if (!musicFiles.isEmpty()) {
            mMusicAdapter = new MusicAdapter(getApplicationContext(), musicFiles,
                    new MusicAdapter.OnMusicClickListener() {
                        @Override
                        public void onMusicClick(int position) {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("position", position);
                            setResult(1, resultIntent);
                            finish();
                        }
                    });
            mRecyclerView.setAdapter(mMusicAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,
                    false));
        }
    }
}
