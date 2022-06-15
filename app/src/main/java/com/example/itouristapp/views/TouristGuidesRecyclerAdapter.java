package com.example.itouristapp.views;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.TourGuide;

import java.util.ArrayList;

public class TouristGuidesRecyclerAdapter extends RecyclerView.Adapter<TouristGuidesRecyclerAdapter.TouristGuidesViewHolder> {

    private ArrayList<TourGuide> tourGuideArrayList;
    private Context context;

    public TouristGuidesRecyclerAdapter(ArrayList<TourGuide> tourGuideArrayList, Context context) {
        this.tourGuideArrayList = tourGuideArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TouristGuidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_tourist_guide, parent, false);

        return new TouristGuidesRecyclerAdapter.TouristGuidesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristGuidesViewHolder holder, int position) {

        TourGuide tourGuide = tourGuideArrayList.get(position);
        holder.tvTourGuideName.setText(tourGuide.getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, SelectGuideActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TouristGuidesViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTourGuideName;
        public View layout;


        public TouristGuidesViewHolder(View view){
            super(view);
            tvTourGuideName = view.findViewById(R.id.card_tg_name);
        }
    }
}
