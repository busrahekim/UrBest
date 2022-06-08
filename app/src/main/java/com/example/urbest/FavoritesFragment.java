package com.example.urbest;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    List<UserFavorites> favs;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userID;
    private TextView t;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.rcv);
        t = view.findViewById(R.id.textView9);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        database = FirebaseDatabase.getInstance().getReference("favList").child(userID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favs = new ArrayList<>();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserFavorites userFavorites = dataSnapshot.getValue(UserFavorites.class);
                    if(userFavorites != null){
                        favs.add(userFavorites);
                        myAdapter = new MyAdapter(favs);
                        recyclerView.setAdapter(new MyAdapter(favs));
                        myAdapter.notifyDataSetChanged();
                        t.setVisibility(View.INVISIBLE);
                    }
                    else {
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}