package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ExampleDialog.ExampleDialogListener {

    private int TAKE_IMG_CODE = 10001;
    private DrawerLayout drawerLayout;
    private TextView profileHead, firstName,lastName,mailText;
    private ImageView profilePic;
    private int id;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileHead = findViewById(R.id.profileHead);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        mailText = findViewById(R.id.mail);
        profilePic = findViewById(R.id.profilePicture);

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fName = userProfile.getFirstName();
                    String lName = userProfile.getLastName();
                    String uMail = userProfile.getMail();

                    firstName.setText(fName);
                    lastName.setText(lName);
                    mailText.setText(uMail);

                    String head = fName + " " + lName;
                    profileHead.setText(head);

                    if(user.getPhotoUrl() != null){
                        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(profilePic);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Something wrong happened!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tabmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:{
                mAuth.signOut();
                Intent i = new Intent(this,TimeSpentService.class);
                stopService(i);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:{
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_explore:{
                Intent intent = new Intent(getApplicationContext(),ExploreActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_profile:{
                break;
            }
            case R.id.nav_walk:{
                Intent intent = new Intent(getApplicationContext(),WalkActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_share:{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Download this app";
                String sub = "http://play.google.com";
                intent.putExtra(Intent.EXTRA_TEXT,body);
                intent.putExtra(Intent.EXTRA_TEXT,sub);
                startActivity(Intent.createChooser(intent,"Share the App"));
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void editProfile(View view) {
        id = 0;
        openDialog(id);
    }

    private void openDialog(int id) {
        ExampleDialog exampleDialog = new ExampleDialog(id);
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    public void changePassword(View view) {
        id = 1;
       openDialog(id);
    }

    @Override
    public void applyText(String firstname, String lastname, String idkText) {
            firstName.setText(firstname);
            lastName.setText(lastname);
            mailText.setText(idkText);

            String head = firstname + " " + lastname;
            profileHead.setText(head);

    }

    public void changeProfilePicture(View view) {
        Intent openGallery = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(openGallery.resolveActivity(getPackageManager()) != null){
            startActivityIfNeeded(openGallery,TAKE_IMG_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TAKE_IMG_CODE){
            switch (resultCode){
                case RESULT_OK:{
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    profilePic.setImageBitmap(bitmap);
                    handleUpload(bitmap);
                }
            }
        }
    }

    private void handleUpload(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        storageReference = FirebaseStorage.getInstance().getReference()
        .child("profileImg")
        .child(userID + ".jpeg");

        storageReference.putBytes(byteArrayOutputStream.toByteArray())
                .addOnSuccessListener(taskSnapshot -> getDownloadUrl(storageReference))
        .addOnFailureListener(e -> Log.e("ImgError","Fail to upload img",e.getCause()));
    }

    private void getDownloadUrl(StorageReference ref) {
        ref.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    Log.d("Success","Succeeded to upload img" + uri );
                    setUserProfileUrl(uri);
                });
    }

    private void setUserProfileUrl(Uri uri){
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(request)
                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(),"Profile image uploaded!",Toast.LENGTH_SHORT).show())
        .addOnFailureListener(e -> Toast.makeText(getApplicationContext(),"Profile image failed!",Toast.LENGTH_SHORT).show());
    }

}
