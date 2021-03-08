package com.eden;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {
   FirebaseDatabase mDatabase;

   DatabaseReference databaseUser;
   FirebaseAuth mAuth;

   TextView user_text, day_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user_text=findViewById(R.id.welcome_text);
        day_time=findViewById(R.id.day_time);

        mDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        databaseUser= mDatabase.getReference("Users");
        String id = mAuth.getCurrentUser().getUid();
         DatabaseReference userName=databaseUser.child(id).child("user_emil");

    }


}