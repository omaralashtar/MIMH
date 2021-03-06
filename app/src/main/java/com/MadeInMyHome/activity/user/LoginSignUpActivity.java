package com.MadeInMyHome.activity.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.MadeInMyHome.databinding.ActivityLoginSignUpBinding;
import com.google.android.material.tabs.TabLayout;

public class LoginSignUpActivity extends AppCompatActivity {

    public static ActivityLoginSignUpBinding binding;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        if (getIntent().getExtras().getString("name").equals("login")) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
        //viewPager.setCurrentItem(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}