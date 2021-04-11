package com.eden.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eden.Models.TreeDetailModel;
import com.eden.R;
import com.eden.OrnamentalInfo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OrnamentalTreeAdapter extends FirebaseRecyclerAdapter<TreeDetailModel, OrnamentalTreeAdapter.myViewHolder> {


    public OrnamentalTreeAdapter(@NonNull FirebaseRecyclerOptions<TreeDetailModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TreeDetailModel model) {
        holder.name.setText(model.getTree_name());
        holder.common_name.setText(model.getCommon_name());
        Glide.with(holder.img.getContext()).
                load(model.getImageUrl()).into(holder.img);
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), OrnamentalInfo.class);
                intent.putExtra("Tree Key", getRef(position).getKey());
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tree_details_single, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, common_name;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ornamentImg);
            name = (TextView) itemView.findViewById(R.id.nametext);
            common_name = (TextView) itemView.findViewById(R.id.commontext);

        }

    }

}
