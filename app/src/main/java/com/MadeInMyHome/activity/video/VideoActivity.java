package com.MadeInMyHome.activity.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MadeInMyHome.R;

public class VideoActivity extends AppCompatActivity {

    VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }
}