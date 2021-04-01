package com.eden.SelectionAlgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Adapters.CropsAdapter;
import com.eden.Crop;
import com.eden.Models.StateModel;
import com.eden.States;
import com.eden.CropsActivity;
import com.eden.Models.CropsModel;
import com.eden.Models.Tree;
import com.eden.R;
import com.eden.utils.LandUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {


    private String TAG;
    private Eden pEden;
    private FarmerInput pFarmerInput;
    TextView selected_state, selected_crop, best_tree, selectedTreeRegion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_result);
        selected_state = findViewById(R.id.state_select);
        selected_crop = findViewById(R.id.crops_select);
        best_tree = findViewById(R.id.best_tree_Display);
        selectedTreeRegion = findViewById(R.id.Tree_region);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("FarmerInput", Context.MODE_PRIVATE);
        String state = preferences.getString("state", "");

        String cropName = preferences.getString("cropName", "");
        String botnical = preferences.getString("BotName", "");
        String shadereq = preferences.getString("shadeReq", "");
        String stateName= preferences.getString("stateName", "");


        double shade_req = Double.parseDouble(shadereq);

        List<Integer> spacing = new ArrayList<Integer>();
        spacing.add(3);
        spacing.add(3);

        List<Tree> trees = new Tree().getTreeDataLocally();
        Crop crop = new Crop(botnical, cropName, spacing, shade_req);


        Double erosionTendency = LandUtil.getRandomErosionTendency();


        //  Crop crop = gson.fromJson(cropString, Crop.class); //convert json string back to Crop object


        pFarmerInput = new FarmerInput(crop, state, erosionTendency);
        //populate the `trees` with list of trees from the database
        pEden = new Eden();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Tree selectedTree = pEden.getBestTreeMatch(pFarmerInput, trees).get();
           String FinalStateDisplay = state;
            String FinalCropName = crop.getCropName();
            String BestTreeDisplayName = selectedTree.name;
            String BestTreeDisplayForest=selectedTree.Location;
            Toast.makeText(getApplicationContext(), BestTreeDisplayForest, Toast.LENGTH_LONG).show();
           selected_state.setText(FinalStateDisplay);
             selected_crop.setText(FinalCropName);
          best_tree.setText(BestTreeDisplayName);
          selectedTreeRegion.setText(BestTreeDisplayForest);

        }


    }
}

