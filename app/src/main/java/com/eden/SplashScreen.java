package com.eden;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.eden.Ui.Sign_in;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseApp firebaseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        firebaseApp=FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent homeIntent;
                if (firebaseUser != null){

                    homeIntent = new Intent(SplashScreen.this, HomePage.class);
                    startActivity(homeIntent);

                }else {
                    homeIntent = new Intent(SplashScreen.this, SignUp.class);
                    startActivity(homeIntent);
                }

                finish();


            }
        }, SPLASH_TIME_OUT);

        //sets the splash screen height and width to no limit
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}