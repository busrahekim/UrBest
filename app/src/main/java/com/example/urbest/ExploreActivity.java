package com.example.urbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.urbest.ExploreParentFragments.BodyHealthFragment;
import com.example.urbest.ExploreParentFragments.MentalHealthFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ExploreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

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
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MentalHealthFragment(), "Mental Health");
        viewPagerAdapter.addFragment(new BodyHealthFragment(), "Body Health");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
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