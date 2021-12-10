package com.MadeInMyHome.activity.splash;

import static com.MadeInMyHome.utilities.constants.SPLASH_TIMER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.MadeInMyHome.MainActivity;
import com.MadeInMyHome.R;

public class splashActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img = findViewById(R.id.imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splashActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, SPLASH_TIMER );
    }
}