package com.example.faucovid_19info;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;


import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.faucovid_19info.ui.main.SectionsPagerAdapter;

import java.util.Objects;

public class TabbedActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Kill the ugly App Title Bar at the top
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_tabbed);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}