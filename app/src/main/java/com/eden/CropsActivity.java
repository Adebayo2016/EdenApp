package com.eden;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Adapters.CropsAdapter;
import com.eden.Models.CropsModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;



public class CropsActivity extends AppCompatActivity  {



    FirebaseFirestore firebaseFirestore;
    RecyclerView mcropList;
   private CropsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_list);
        Intent intents=getIntent();
        String receivedState=intents.getStringExtra("sendState");
        String receivedForest=intents.getStringExtra("sendForest");
        double receivedTendency=intents.getDoubleExtra("sendTendency",30.00);
        FirebaseApp.initializeApp(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mcropList = findViewById(R.id.crop_list);

        Query query = firebaseFirestore.collection("Crops");
        FirestoreRecyclerOptions<CropsModel> options = new FirestoreRecyclerOptions.Builder<CropsModel>().
                setQuery(query, CropsModel.class).
                build();

        adapter = new CropsAdapter(options);

        mcropList.setHasFixedSize(true);
        mcropList.setLayoutManager(new LinearLayoutManager(this));
        mcropList.setAdapter(adapter);
        adapter.setOnItemClickListener(new CropsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(DocumentSnapshot documentSnapshot, int position) {
                Crop crop = documentSnapshot.toObject(Crop.class);

                //we will pass this crop to the Processing class
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("FarmerInput", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                Gson gson = new Gson();
                editor.putString("cropdata", gson.toJson(crop)); //convert Crop Object to a json string before storing in SPrefs
                editor.apply();

               // Intent intent = new Intent(CropsActivity.this, Processing.class);
                //startActivity(intent);



            }
        });
    }



    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    }

