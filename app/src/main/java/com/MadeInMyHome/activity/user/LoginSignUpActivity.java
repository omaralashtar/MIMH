package com.MadeInMyHome.activity.user;

import android.os.Bundle;

import com.MadeInMyHome.databinding.ActivityLoginSignUpBinding;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSignUpActivity extends AppCompatActivity {

    private ActivityLoginSignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        //viewPager.setCurrentItem(0);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}