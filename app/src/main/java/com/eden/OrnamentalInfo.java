package com.eden;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrnamentalInfo extends AppCompatActivity {
    ImageView img;
    DatabaseReference ref;
    TextView treeDetailText;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_info);
        img = findViewById(R.id.tree_detailed_img);
        treeDetailText=findViewById(R.id.tree_detailed_info);
        ref= FirebaseDatabase.getInstance().getReference().child(" Ornamental Trees");
        String TreeKey=getIntent().getStringExtra("Tree Key");
        ref.child(TreeKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String tree_desc=snapshot.child("description").getValue().toString();
                    String imageUrl=snapshot.child("imageUrl").getValue().toString();
                    Glide.with(img.getContext()).load(imageUrl).into(img);
                    treeDetailText.setText(tree_desc);

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to get Details", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
