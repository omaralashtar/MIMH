package com.MadeInMyHome.activity;

import static com.MadeInMyHome.utilities.constants.SPLASH_TIMER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.introduction.IntroductionActivity;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.activity.user.showProfileToUser.showProfileToUser;
import com.MadeInMyHome.activity.welcom.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, IntroductionActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMER );
    }
}