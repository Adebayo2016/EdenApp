package com.eden.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eden.Models.CropsModel;
import com.eden.Models.StateModel;
import com.eden.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;


public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.CropsHolder> {
    private OnItemClickedListener mListener;

    private ArrayList<CropsModel> mCropList;

    public interface OnItemClickedListener{
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(CropsAdapter.OnItemClickedListener listener){
        mListener=listener;
    }

    @NonNull
    @Override
    public CropsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.crops_item_list,parent, false);
        CropsHolder cropsHolder=new CropsHolder(v, (OnItemClickedListener) mListener);
        return cropsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CropsHolder holder, int position) {
        CropsModel currentCrop= mCropList.get(position);
        holder.crop.setText(currentCrop.getCropName());
        holder.botanical_name.setText(currentCrop.getBotanicalName());

    }

    @Override
    public int getItemCount() {
        return mCropList.size();
    }

    class CropsHolder extends RecyclerView.ViewHolder{


        private TextView crop;
        private TextView botanical_name;

        public CropsHolder(@NonNull View itemView, CropsAdapter.OnItemClickedListener listener) {
            super(itemView);
            crop = itemView.findViewById(R.id.crop_name);
            botanical_name = itemView.findViewById(R.id.Botanical);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position =getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }

                }
            });


        }



    }

    public CropsAdapter(ArrayList<CropsModel> cropItem) {
        mCropList =cropItem;

    }

}



