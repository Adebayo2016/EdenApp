package com.eden.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eden.HomePage;
import com.eden.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Sign_in  extends AppCompatActivity {


    FirebaseAuth mAuth;
    public EditText email_text, password_log;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth=FirebaseAuth.getInstance();


       email_text=findViewById(R.id.email_login);
        password_log=findViewById(R.id.password_login);




    }



    public void login(View view) {

       final  String email;
      final  String password;

    email=email_text.getText().toString();
    password=password_log.getText().toString();


    if(email.isEmpty()){
        email_text.requestFocus();
        email_text.setError("please enter your mail");

    }


    if (password.isEmpty()){
        password_log.requestFocus();
        password_log.setError("please enter your password");

    }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging you in...");
        progressDialog.show();


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){


                    Intent intent =new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_LONG).show();

                }

            }
        });



    }
}
