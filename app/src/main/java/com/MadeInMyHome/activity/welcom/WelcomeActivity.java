package com.MadeInMyHome.activity.welcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.activity.ui.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button loginWelcome,signupWelcome,visitWelcome;
//ActivityWelcomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        loginWelcome=findViewById(R.id.loginWelcome);
        signupWelcome=findViewById(R.id.signupWelcome);
        visitWelcome=findViewById(R.id.visitWelcome);


visitWelcome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Toast.makeText(WelcomeActivity.this, "jjjjjjjjjj", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(WelcomeActivity.this, MainActivity.class);

        startActivity(i);
        Toast.makeText(WelcomeActivity.this, "jkkpkkiip", Toast.LENGTH_SHORT).show();



    }
});


      loginWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(WelcomeActivity.this, LoginSignUpActivity.class);

                startActivity(i);

            }
        });





    }
}