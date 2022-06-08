package com.example.urbest.ExploreChildFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.urbest.R;
import com.example.urbest.ReceiptActivity;

public class ReceiptFragment extends Fragment implements View.OnClickListener {

    CardView cardView0,cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

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
        cardView5 = view.findViewById(R.id.c5);
        cardView5.setOnClickListener(this);
        cardView6 = view.findViewById(R.id.c6);
        cardView6.setOnClickListener(this);
        cardView7 = view.findViewById(R.id.c7);
        cardView7.setOnClickListener(this);
        cardView8 = view.findViewById(R.id.c8);
        cardView8.setOnClickListener(this);
        cardView9 = view.findViewById(R.id.c9);
        cardView9.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.c0:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",0);
                startActivity(intent);
                break;
            }
            case R.id.c1:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",1);
                startActivity(intent);
                break;
            }
            case R.id.c2:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",2);
                startActivity(intent);
                break;
            }
            case R.id.c3:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",3);
                startActivity(intent);
                break;
            }
            case R.id.c4:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",4);
                startActivity(intent);
                break;
            }
            case R.id.c5:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",5);
                startActivity(intent);
                break;
            }
            case R.id.c6:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",6);
                startActivity(intent);
                break;
            }
            case R.id.c7:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",7);
                startActivity(intent);
                break;
            }
            case R.id.c8:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",8);
                startActivity(intent);
                break;
            }
            case R.id.c9:{
                Intent intent = new Intent(getContext(),ReceiptActivity.class);
                intent.putExtra("KEY",9);
                startActivity(intent);
                break;
            }

        }
    }
}