package com.MadeInMyHome.activity.welcom;

import static com.MadeInMyHome.utilities.constants.ISVISITOR;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.databinding.ActivityWelcomBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityWelcomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ISVISITOR=false;

        binding.visitWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                ISVISITOR=true;
                startActivity(i);
            }
        });

        binding.loginWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, LoginSignUpActivity.class);
                i.putExtra("name","login");
                startActivity(i);
            }
        });
        binding.signupWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, LoginSignUpActivity.class);
                i.putExtra("name","signup");
                startActivity(i);
            }
        });
    }
}