package com.MadeInMyHome.activity.video;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.adapter.RecycleAdapterVideo;
import com.MadeInMyHome.databinding.ActivityVideoBinding;
import com.MadeInMyHome.model.Video;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    VideoViewModel videoViewModel;
    RecycleAdapterVideo recycleAdapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        binding.videoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        videoViewModel.getVideos(this, getIntent().getExtras().getString("id")).observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                recycleAdapterVideo = new RecycleAdapterVideo(VideoActivity.this, videos, binding.webView);
                binding.videoRecycler.setAdapter(recycleAdapterVideo);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}