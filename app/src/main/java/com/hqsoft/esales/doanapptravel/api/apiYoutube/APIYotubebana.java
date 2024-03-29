package com.hqsoft.esales.doanapptravel.api.apiYoutube;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.hqsoft.esales.doanapptravel.R;

public class APIYotubebana extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    int REQUEST_VIDEO = 123;
    String API_KEY = "AIzaSyBDqUt-sg1bOfP71ZLoI0sd33elzr7Dep4";
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiyotubebana);

        youTubePlayerView = findViewById(R.id.APIyoutube);
        youTubePlayerView.initialize(API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("Ggq5UVbKlPE");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_VIDEO);
        }else {
            Toast.makeText(this, "ERR", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(API_KEY, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}