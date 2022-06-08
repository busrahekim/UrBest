package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.urbest.ExploreParentFragments.BodyHealthFragment;
import com.example.urbest.ExploreParentFragments.MentalHealthFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;
    RadioButton calm, stress, tired, happy, sad;
    boolean isClicked;
    Button b;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        calm = findViewById(R.id.calmButton);
        stress = findViewById(R.id.stressButton);
        tired = findViewById(R.id.tiredButton);
        happy = findViewById(R.id.happyButton);
        sad = findViewById(R.id.sadButton);
        isClicked = false;

        RadioGroup radioGroup = findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.calmButton) {
                Toast.makeText(getApplicationContext(), "By staying calm, you increase your resistance against any kind of storms.\n-Murat İldan",
                        Toast.LENGTH_LONG).show();
            } else if(checkedId == R.id.stressButton) {
                Toast.makeText(getApplicationContext(), "More smiling, less worrying. More compassion, less judgment. More blessed, less stressed. More love, less hate.\n― Roy T. Bennett",
                        Toast.LENGTH_LONG).show();
            } else if(checkedId == R.id.tiredButton){
                Toast.makeText(getApplicationContext(), "If you got tired; learn to rest, not to quit.\n-Unknown",
                        Toast.LENGTH_LONG).show();
            } else if(checkedId == R.id.happyButton){
                Toast.makeText(getApplicationContext(), "Being happy never goes out of style.\n-Lilly Pulitzer",
                        Toast.LENGTH_LONG).show();
            } else if(checkedId == R.id.sadButton){
                Toast.makeText(getApplicationContext(), "Even the darkest night will end and the sun will rise.\n-Les Misérables",
                        Toast.LENGTH_LONG).show();
            }
        });

        b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DelayedMessageService.class);
            intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,
                    getResources().getString(R.string.response));
            startService(intent);
            Toast.makeText(getApplicationContext(),"Look at the mirror!",Toast.LENGTH_LONG).show();
        });

        mAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        addFragment();

    }

    private void addFragment() {

        viewPager = findViewById(R.id.homeViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new FavoritesFragment(), "Favorites");
        viewPager.setAdapter(viewPagerAdapter);

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
               break;
           }
           case R.id.nav_explore:{
               Intent intent = new Intent(getApplicationContext(),ExploreActivity.class);
               startActivity(intent);
               break;
           }
           case R.id.nav_profile:{
               Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
               startActivity(intent);
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

}