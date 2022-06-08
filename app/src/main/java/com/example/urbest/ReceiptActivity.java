package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import static com.example.urbest.R.drawable.ic_baseline_star_24;

public class ReceiptActivity extends AppCompatActivity {

    private TextView ingredients, howToMake;
    private ImageView headImg;
    private ArrayList<String> ingr, how, head;
    private ArrayList<Integer> img;
    private CollapsingToolbarLayout c;
    private int key;
    private FloatingActionButton floatingActionButton;
    private boolean clicked = false;
    Receipt receipt;

    DatabaseReference favDB;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userID;
    private CoordinatorLayout co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        co = findViewById(R.id.c);

        receipt = new Receipt();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        key = bundle.getInt("KEY");

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        favDB = FirebaseDatabase.getInstance().getReference().child("favList").child(userID);


        ingredients = findViewById(R.id.content);
        howToMake = findViewById(R.id.howToMake);
        headImg = findViewById(R.id.headImage);
        c = findViewById(R.id.collapsing_toolbar);
        floatingActionButton = findViewById(R.id.fab);

        ingr = receipt.getIngredients();
        how = receipt.getHowToMake();
        head = receipt.getHeaders();
        img = receipt.getImages();

        ingredients.setText(ingr.get(key));
        howToMake.setText(how.get(key));
        c.setTitle(head.get(key));
        headImg.setImageResource(img.get(key));
    }

    public void fabClicked(View view) {
        if(clicked == false){
            floatingActionButton.setImageResource(ic_baseline_star_24);
            insertFavData();
            Snackbar snackbar = Snackbar.make(co, "The recipe added your favorites.", Snackbar.LENGTH_LONG);
            snackbar.setAction("OKEY", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
            snackbar.show();
            clicked = true;
        }
        else{
            floatingActionButton.setImageResource(R.drawable.ic_baseline_star_border_24);
            removeFavData();
            Snackbar snackbar = Snackbar.make(co, "The recipe removed from your favorites.", Snackbar.LENGTH_LONG);
            snackbar.setAction("OKEY", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
            clicked = false;
        }
    }

    private void removeFavData() {
        favDB.child("favList").child(userID).removeValue();
    }

    private void insertFavData() {
        String headData = receipt.getHeaders().get(key);
        int imgData =  receipt.getImages().get(key);
        UserFavorites u = new UserFavorites(imgData,headData);

        favDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount() == 0){
                    Log.d("count",snapshot.getChildrenCount()+"");
                    favDB.push().setValue(u);
                    return;
                }
                else{
                    boolean checkIfExist = checkIfExist(headData,snapshot);
                    if(checkIfExist == false){
                        favDB.push().setValue(u);
                        Log.d("not exist","db has not the favorite");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean checkIfExist(String a, DataSnapshot snapshot) {

        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
            UserFavorites userFavorites = dataSnapshot.getValue(UserFavorites.class);
            if (a.equals(userFavorites.getFavoriteName())){
                Log.d("exist","db has the favorite");
                return true;
            }
        }
        return false;
    }
}