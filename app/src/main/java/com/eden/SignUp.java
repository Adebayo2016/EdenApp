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
    public EditText confirm;
    public String full_name;
    public  String user_mail;
    public  String user_pass;
    public String   confirm_pass;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebe

       mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email_reg);
        password=findViewById(R.id.password_reg);
        username=findViewById(R.id.name_reg);
        confirm=findViewById(R.id.confirm_password_reg);

    }

    public void Register(View view) {

        full_name=username.getText().toString();
        user_mail=email.getText().toString().trim();
        user_pass=password.getText().toString();
        confirm_pass=confirm.getText().toString();


        if(full_name.isEmpty()){
            username.requestFocus();
            username.setError("please enter your full name");

        }

        if (user_mail.isEmpty()){
            email.requestFocus();
            email.setError("please enter your mail");

        }

        if(user_mail.matches(emailPattern)){


        }else {

            email.requestFocus();
            email.setError("please enter valid mail");
        }




        if(user_pass.isEmpty()){

            password.requestFocus();
            password.setError("please enter a password");

        }

        if(!confirm_pass.equals(user_pass)){
            confirm.requestFocus();
            confirm.setError("passwords do no match");
        }


    }
}