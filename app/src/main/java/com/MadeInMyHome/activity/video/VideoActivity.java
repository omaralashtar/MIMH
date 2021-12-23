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