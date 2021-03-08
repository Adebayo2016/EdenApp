package com.eden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public EditText email;
    public EditText password;
    public EditText username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();

    }

    public void Register(View view) {



    }
}