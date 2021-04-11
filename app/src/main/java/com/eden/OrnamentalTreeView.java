package com.eden;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Adapters.OrnamentalTreeAdapter;
import com.eden.Models.TreeDetailModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class OrnamentalTreeView extends AppCompatActivity {
    RecyclerView recyclerView;
    OrnamentalTreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tree_detail_items);

        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TreeDetailModel> options = new FirebaseRecyclerOptions.
                Builder<TreeDetailModel>()
                .setQuery(FirebaseDatabase.getInstance().
                        getReference().child("Ornamental Trees"), TreeDetailModel.class).build();


        adapter = new OrnamentalTreeAdapter(options);
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
