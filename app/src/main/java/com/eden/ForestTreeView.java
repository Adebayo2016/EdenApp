package com.eden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Adapters.TreeDetailsAdapter;
import com.eden.Models.TreeDetailModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ForestTreeView extends AppCompatActivity {
    RecyclerView recyclerView;
    TreeDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_detail_items);

        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TreeDetailModel> options = new FirebaseRecyclerOptions.
                Builder<TreeDetailModel>()
                .setQuery(FirebaseDatabase.getInstance().
                        getReference().child("Forest Trees"), TreeDetailModel.class).build();


        adapter = new TreeDetailsAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}
