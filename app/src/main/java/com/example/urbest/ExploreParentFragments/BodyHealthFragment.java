package com.example.urbest.ExploreParentFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urbest.ExploreChildFragments.ExerciseFragment;
import com.example.urbest.ExploreChildFragments.ReceiptFragment;
import com.example.urbest.R;
import com.example.urbest.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class BodyHealthFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_body_health, container, false);

        addFragment(view);
        return view;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.bodyTabLayout);
        viewPager = view.findViewById(R.id.bodyViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ExerciseFragment(),"Exercise");
        viewPagerAdapter.addFragment(new ReceiptFragment(),"Receipts");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}