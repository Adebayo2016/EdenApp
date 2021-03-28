package com.eden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eden.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    public FirebaseAuth mAuth;
    public EditText email;
    public EditText password;
    public EditText username;
    public EditText confirm;
    public String full_name;
    public  String user_mail;
    public  String user_pass;
    public String   confirm_pass;
    DatabaseReference mDatabase;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);

        mAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
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
            return;

        }

        if (user_mail.isEmpty()){
            email.requestFocus();
            email.setError("please enter your mail");
            return;

        }

        if(user_mail.matches(emailPattern)){


        }else {

            email.requestFocus();
            email.setError("please enter valid mail");
            return;
        }


        if(user_pass.isEmpty()){

            password.requestFocus();
            password.setError("please enter a password");
            return;

        }

        if(!confirm_pass.equals(user_pass)){
            confirm.requestFocus();
            confirm.setError("passwords do no match");
        }


        mAuth.createUserWithEmailAndPassword(user_mail,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                if (task.isSuccessful()){

                    User user = new User(full_name,user_mail);

                    FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance()
                            .getCurrentUser())).getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {

                            if (task.isSuccessful()) {
                               // progressDialog.dismiss();
                                GoToHomePage();

                            } else {
                                //progressDialog.dismiss();

                                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }

                        }


                    });




                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_LONG).show();

                }
        }
    });


}

    private void GoToHomePage() {

        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
        finish();
    }
}