package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;

public class PodcastActivity extends AppCompatActivity {

    JcPlayerView jcplayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);


        String url = "https://firebasestorage.googleapis.com/v0/b/auth-20cca.appspot.com/o/podcasts%2F3%20Minute%20Breathing%20Space.mp3?alt=media&token=accbc931-f849-46bf-ad9c-1abd74902ff3";
        String url1 = "https://firebasestorage.googleapis.com/v0/b/auth-20cca.appspot.com/o/podcasts%2FStress%20and%20the%20Mind.mp3?alt=media&token=cd7e9c2d-af2a-4a8a-b7f0-dbf1f33a1341";
        String url2 = "https://firebasestorage.googleapis.com/v0/b/auth-20cca.appspot.com/o/podcasts%2FWellbeing%20and%20Positive%20Thinking.mp3?alt=media&token=3e06d914-667c-4260-bb52-3beeb2d1bc57";
        String url3 = "https://firebasestorage.googleapis.com/v0/b/auth-20cca.appspot.com/o/podcasts%2FWhat%20is%20Mindfulness.mp3?alt=media&token=e0aee27e-98e6-4c8d-be41-1a7cb334a88b";


         jcplayerView = (JcPlayerView) findViewById(R.id.jcplayer);
        ArrayList<JcAudio> jcAudios = new ArrayList<>();
        jcAudios.add(JcAudio.createFromURL("Breathing Space",url));
        jcAudios.add(JcAudio.createFromURL("Stress and the Mind",url1));
        jcAudios.add(JcAudio.createFromURL("Positive Thinking",url2));
        jcAudios.add(JcAudio.createFromURL("What is Mindfulness ?",url3));

        jcplayerView.initPlaylist(jcAudios, null);
        jcplayerView.createNotification();

    }


}