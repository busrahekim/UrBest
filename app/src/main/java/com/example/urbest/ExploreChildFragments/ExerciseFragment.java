package com.example.urbest.ExploreChildFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.urbest.ExerciseActivity;
import com.example.urbest.R;


public class ExerciseFragment extends Fragment implements View.OnClickListener {

    CardView cardView0,cardView1,cardView2,cardView3,cardView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        cardView0 = view.findViewById(R.id.c0);
        cardView0.setOnClickListener(this);
        cardView1 = view.findViewById(R.id.c1);
        cardView1.setOnClickListener(this);
        cardView2 = view.findViewById(R.id.c2);
        cardView2.setOnClickListener(this);
        cardView3 = view.findViewById(R.id.c3);
        cardView3.setOnClickListener(this);
        cardView4 = view.findViewById(R.id.c4);
        cardView4.setOnClickListener(this);
       return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.c0:{
                Intent intent = new Intent(getContext(), ExerciseActivity.class);
                intent.putExtra("KEY",0);
                startActivity(intent);
                break;
            }
            case R.id.c1:{
                Intent intent = new Intent(getContext(),ExerciseActivity.class);
                intent.putExtra("KEY",1);
                startActivity(intent);
                break;
            }
            case R.id.c2:{
                Intent intent = new Intent(getContext(),ExerciseActivity.class);
                intent.putExtra("KEY",2);
                startActivity(intent);
                break;
            }
            case R.id.c3:{
                Intent intent = new Intent(getContext(),ExerciseActivity.class);
                intent.putExtra("KEY",3);
                startActivity(intent);
                break;
            }
            case R.id.c4:{
                Intent intent = new Intent(getContext(),ExerciseActivity.class);
                intent.putExtra("KEY",4);
                startActivity(intent);
                break;
            }
        }
    }
}