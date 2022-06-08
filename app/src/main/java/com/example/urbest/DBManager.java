package com.example.urbest;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBManager {

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;
    private int size ;

    public DBManager() {
        rootNode = FirebaseDatabase.getInstance("https://urbest2021sw-default-rtdb.europe-west1.firebasedatabase.app");
        mDatabase = rootNode.getReference("users");
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void writeNewUser(String userId,String userName, String password, String firstName, String lastName, String mail) {
        User user = new User(userName, firstName, lastName, password, mail);

        mDatabase.child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        size = (int) dataSnapshot.getChildrenCount();
                        Log.d("id",String.valueOf(size));
                        String userId = String.valueOf(size);
                        mDatabase.child(userId).setValue(user);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public User getUserName(String userId){
        User user = new User();
        mDatabase.child("users").child(userId).child("username").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                Log.d("firebaseUsername", String.valueOf(task.getResult().getValue(String.class)));
                user.setUsername(String.valueOf(task.getResult().getValue(String.class)));
            }
        });
        mDatabase.child("users").child(userId).child("password").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                Log.d("firebasePassword", String.valueOf(task.getResult().getValue(String.class)));
                user.setPassword(String.valueOf(task.getResult().getValue(String.class)));
            }
        });

        return user;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize(){
        mDatabase.child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        size = (int) dataSnapshot.getChildrenCount();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        Log.d("id",String.valueOf(size));
        return size;
    }
}
