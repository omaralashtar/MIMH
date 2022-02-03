package com.MadeInMyHome.activity.welcom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.ViewModel.SendMessageViewModel;
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


        binding.visitWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
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