package com.MadeInMyHome.activity;

import static com.MadeInMyHome.utilities.General.getSharedPreference;
import static com.MadeInMyHome.utilities.General.getToken;
import static com.MadeInMyHome.utilities.constants.SPLASH_TIMER;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.introduction.IntroductionActivity;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.ui.MainViewModel;
import com.MadeInMyHome.activity.welcom.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getToken(SplashActivity.this).equals("")) {
                    if (getSharedPreference(SplashActivity.this, "intro").equals("")) {
                        i = new Intent(SplashActivity.this, IntroductionActivity.class);
                    } else {
                        i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    }
                    startActivity(i);
                    finish();
                } else {
                    mainViewModel.checkToken(SplashActivity.this,getToken(SplashActivity.this)).observe(SplashActivity.this, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if(s.equals("1")) {
                                i = new Intent(SplashActivity.this, MainActivity.class);
                            }else {
                                i = new Intent(SplashActivity.this, WelcomeActivity.class);
                            }
                            startActivity(i);
                            finish();
                        }
                    });
                }
            }
        }, SPLASH_TIMER);
    }
}