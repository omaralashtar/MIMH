package com.MadeInMyHome.activity.welcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.LoginSignUp.LoginSignUpActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button loginWelcome,signupWelcome,visitWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        loginWelcome=findViewById(R.id.loginWelcome);
        signupWelcome=findViewById(R.id.signupWelcome);
        visitWelcome=findViewById(R.id.visitWelcome);





        loginWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(WelcomeActivity.this, LoginSignUpActivity.class);

                startActivity(i);

            }
        });





    }
}