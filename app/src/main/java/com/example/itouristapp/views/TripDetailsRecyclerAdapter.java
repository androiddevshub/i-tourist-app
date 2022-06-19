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
import com.example.itouristapp.models.Trip;

import java.util.ArrayList;

public class TripDetailsRecyclerAdapter extends RecyclerView.Adapter<TripDetailsRecyclerAdapter.TripDetailsViewHolder> {

    private ArrayList<Trip> tripArrayList;
    private Context context;
    private String userRole;
    private String action;

    public TripDetailsRecyclerAdapter(ArrayList<Trip> tripArrayList, Context context, String userRole, String action) {
        Log.v("in adapter", "constructor");
        this.tripArrayList = tripArrayList;
        this.context = context;
        this.userRole = userRole;
        this.action = action;
    }

    @NonNull
    @Override
    public TripDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_trip, parent, false);

        return new TripDetailsRecyclerAdapter.TripDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripDetailsViewHolder holder, int position) {
        Trip trip = tripArrayList.get(position);

        holder.name.setText(trip.getName());
        holder.location.setText(trip.getLocation());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (userRole.equals("guide")){
                   intent = new Intent(context, TourGuideTripActivity.class);
                }else{
                   intent = new Intent(context, TouristSelectTripActivity.class);
                }

                intent.putExtra("trip", trip);
                intent.putExtra("action", action);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripArrayList.size();
    }

    public class TripDetailsViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView location;
        public View layout;

        public TripDetailsViewHolder(View view){
            super(view);

            layout = view;
            name = view.findViewById(R.id.tv_trip_name);
            location = view.findViewById(R.id.tv_trip_destination);

        }
    }

}
