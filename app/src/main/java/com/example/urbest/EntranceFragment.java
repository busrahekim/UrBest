package com.example.urbest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;


public class EntranceFragment extends Fragment {

    ProgressBar progressBar;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entrance, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        textView = view.findViewById(R.id.textView);
        progressBar.setMax(100);
        progressAnimation();
        return view;
    }

    private void progressAnimation() {
        ProgressAnimation animation = new ProgressAnimation(this,progressBar,0f,100f);
        animation.setDuration(5000);
        progressBar.setAnimation(animation);

        Animation textAnimation = AnimationUtils.loadAnimation(getContext() ,R.anim.anim);
        textView.startAnimation(textAnimation);
    }
}