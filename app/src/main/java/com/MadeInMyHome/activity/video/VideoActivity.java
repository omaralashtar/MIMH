package com.MadeInMyHome.activity.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterVideo;
import com.MadeInMyHome.databinding.ActivityMainBinding;
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
        setContentView(R.layout.activity_video);

        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);

        binding.videorecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Toast.makeText(this, getIntent().getExtras().getString("id")+"", Toast.LENGTH_SHORT).show();

        videoViewModel.getVideos(this,getIntent().getExtras().getString("id")).observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                recycleAdapterVideo = new RecycleAdapterVideo(VideoActivity.this, videos,binding.webView);
                binding.videorecycler.setAdapter(recycleAdapterVideo);
            }
        });
        /*using object tag

        String html = "<object width='400' height='400' data=\"http://www.youtube.com/embed/XGSy3_Czz8k\"></object>";


        (OR)

                using iframe tag

        String html ="<iframe width='420' height='345' src=\"http://www.youtube.com/embed/XGSy3_Czz8k\"></iframe>";


        webview.loadDataWithBaseURL("", html,
                mimeType, encoding, "");
         webView.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");*/
    }
}