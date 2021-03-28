package com.eden;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Adapters.CropsAdapter;
import com.eden.Adapters.StateAdapter;
import com.eden.Models.CropsModel;
import com.eden.Models.StateModel;
import com.eden.SelectionAlgo.Result;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;

import java.util.ArrayList;


public class CropsActivity extends AppCompatActivity {



    FirebaseFirestore firebaseFirestore;
  private  RecyclerView mcropList;
    SharedPreferences prefCropName,prefBota,prefShade;
    private RecyclerView mRecyclerView;
    private CropsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

//    private  FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_list);
        firebaseFirestore=FirebaseFirestore.getInstance();
        ArrayList<CropsModel> cropItem = new ArrayList<>();

        cropItem.add(new CropsModel("Cocoa","bnn",3));
        cropItem.add(new CropsModel("bnn","bnnn",3));
        cropItem.add(new CropsModel("bnn","bnnn",3));
        cropItem.add(new CropsModel("bnn","bnnn",3));
        cropItem.add(new CropsModel("bnn","bnnn",3));



        mRecyclerView = findViewById(R.id.crop_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CropsAdapter(cropItem);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new CropsAdapter.OnItemClickedListener() {
            @Override
            public void OnItemClick(int position) {
                final String crop_Names = cropItem.get(position).getCropName();
                final String crop_Botni = cropItem.get(position).getBotanicalName();
                final Double crop_shade= cropItem.get(position).getShadeRequirement();
                final String  shadeReq=Double.toString(crop_shade);
                Intent intent = new Intent(CropsActivity.this, Result.class);
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("FarmerInput", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("cropName", crop_Names);
                editor.putString("BotName", crop_Botni);
                editor.putString("shadeReq",  shadeReq);
                editor.apply();
                startActivity(intent);

            }
        }
        );



















    /*    Query query = firebaseFirestore.collection("Crops").orderBy("CropName");
        FirestoreRecyclerOptions<CropsModel> options = new FirestoreRecyclerOptions.Builder<CropsModel>().
                setQuery(query, CropsModel.class).
                build(); *

      adapter =new FirestoreRecyclerAdapter<CropsModel, CropsViewHolder>(options) {


            @NonNull
            @Override
            public CropsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.crops_item_list, parent,false);
                return new CropsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CropsViewHolder holder, int position, @NonNull CropsModel model) {
                holder.crop.setText(model.getCropName());
                holder.botanical_name.setText(model.getBotanicalName());
            }
        };
        mcropList.setHasFixedSize(true);
        mcropList.setLayoutManager(new LinearLayoutManager(this));
        mcropList.setAdapter(adapter);
}

    private class CropsViewHolder extends  RecyclerView.ViewHolder{

        private TextView crop;
        private TextView botanical_name;


        public CropsViewHolder(@NonNull View itemView) {
            super(itemView);
            crop = itemView.findViewById(R.id.crop_name);
            botanical_name = itemView.findViewById(R.id.Botanical);

        }



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
    }*/


}
}


