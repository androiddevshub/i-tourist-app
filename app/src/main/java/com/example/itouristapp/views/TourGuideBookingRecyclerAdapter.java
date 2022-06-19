package com.example.itouristapp.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.TourGuideBooking;

import java.util.ArrayList;

public class TourGuideBookingRecyclerAdapter extends RecyclerView.Adapter<TourGuideBookingRecyclerAdapter.TourGuideBookingViewHolder> {

    private ArrayList<TourGuideBooking> tourGuideBookingArrayList;
    private Context context;

    public TourGuideBookingRecyclerAdapter(ArrayList<TourGuideBooking> tourGuideBookingArrayList, Context context) {
        this.tourGuideBookingArrayList = tourGuideBookingArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TourGuideBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_booking, parent, false);

        return new TourGuideBookingRecyclerAdapter.TourGuideBookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TourGuideBookingViewHolder holder, int position) {

        TourGuideBooking tourGuideBooking = tourGuideBookingArrayList.get(position);

        holder.tvDestinationName.setText(tourGuideBooking.getDestination_name());
        holder.tvDestinationLocation.setText(tourGuideBooking.getDestination_location());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, TourGuideBookingActivity.class);
                intent.putExtra("tourGuideBooking", tourGuideBooking);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourGuideBookingArrayList.size();
    }

    public class TourGuideBookingViewHolder extends RecyclerView.ViewHolder{

        public TextView tvDestinationName, tvDestinationLocation;
        public View layout;


        public TourGuideBookingViewHolder(View view){
            super(view);
            tvDestinationName = view.findViewById(R.id.tv_trip_booking_name);
            tvDestinationLocation = view.findViewById(R.id.tv_trip_booking_destination);
            layout = view;
        }
    }
}
