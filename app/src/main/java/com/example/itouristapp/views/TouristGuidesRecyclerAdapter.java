package com.example.itouristapp.views;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TouristGuidesRecyclerAdapter extends RecyclerView.Adapter<TouristGuidesRecyclerAdapter.TouristGuidesViewHolder> {

    @NonNull
    @Override
    public TouristGuidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TouristGuidesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TouristGuidesViewHolder extends RecyclerView.ViewHolder{

        public TouristGuidesViewHolder(View view){
            super(view);
        }
    }
}
