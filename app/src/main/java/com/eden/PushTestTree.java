package com.eden;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eden.Models.TreeData;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PushTestTree  extends AppCompatActivity {
   // FirebaseDatabase mDatabase;
    DatabaseReference mDatabase;
    TreeData treeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_tree);

        FirebaseApp.initializeApp(this);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Ornamental Trees");

      treeData =new TreeData();


    }

    public void upload(View view) {

        treeData.setTree_name("");
        treeData.setCommon_name("");
        treeData.setDescription("");
        treeData.setImageUrl("");
        mDatabase.push().setValue(treeData);
        Toast.makeText(this,"pushed", Toast.LENGTH_LONG).show();



    }
}
