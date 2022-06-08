package com.example.urbest.ExploreParentFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urbest.BreathActivity;
import com.example.urbest.MeditationActivity;
import com.example.urbest.PodcastActivity;
import com.example.urbest.R;
import com.example.urbest.StoryActivity;


public class MentalHealthFragment extends Fragment implements View.OnClickListener {

    CardView cardView0,cardView1,cardView2,cardViewStory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mental_health, container, false);
        cardView0 = view.findViewById(R.id.c0);
        cardView0.setOnClickListener(this);
        cardViewStory = view.findViewById(R.id.cStory);
        cardViewStory.setOnClickListener(this);
        cardView1 = view.findViewById(R.id.c1);
        cardView1.setOnClickListener(this);
        cardView2 = view.findViewById(R.id.c2);
        cardView2.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.c0:{
                Intent intent = new Intent(getContext(), PodcastActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.cStory:{
                Intent intent = new Intent(getContext(), StoryActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.c1:{
                Intent intent = new Intent(getContext(), BreathActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.c2:{
                Intent intent = new Intent(getContext(), MeditationActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}