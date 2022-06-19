package com.example.itouristapp.views;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.TourGuide;

import java.util.ArrayList;

public class TourGuidesRecyclerAdapter extends RecyclerView.Adapter<TourGuidesRecyclerAdapter.TouristGuidesViewHolder> {

    private ArrayList<TourGuide> tourGuideArrayList;
    private Context context;
    private Integer tripId;

    public TourGuidesRecyclerAdapter(ArrayList<TourGuide> tourGuideArrayList, Context context, Integer tripId) {
        this.tourGuideArrayList = tourGuideArrayList;
        this.context = context;
        this.tripId = tripId;
    }

    @NonNull
    @Override
    public TouristGuidesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_tourist_guide, parent, false);

        return new TourGuidesRecyclerAdapter.TouristGuidesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristGuidesViewHolder holder, int position) {

        Log.v("in adapter", "yes yes");

        TourGuide tourGuide = tourGuideArrayList.get(position);
        holder.tvTourGuideName.setText(tourGuide.getName());
        holder.tvTourGuideLanguages.setText(tourGuide.getLanguages());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, SelectTourGuideActivity.class);
                intent.putExtra("tourGuide", tourGuide);
                intent.putExtra("destinationId", tripId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourGuideArrayList.size();
    }

    public class TouristGuidesViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTourGuideName, tvTourGuideLanguages;
        public View layout;


        public TouristGuidesViewHolder(View view){
            super(view);
            tvTourGuideName = view.findViewById(R.id.card_tg_name);
            tvTourGuideLanguages = view.findViewById(R.id.card_tg_languages);
            layout = view;
        }
    }
}
