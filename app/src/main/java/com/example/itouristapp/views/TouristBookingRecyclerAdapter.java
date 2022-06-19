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
import com.example.itouristapp.models.TouristBooking;

import java.util.ArrayList;

public class TouristBookingRecyclerAdapter extends RecyclerView.Adapter<TouristBookingRecyclerAdapter.TouristBookingViewHolder> {


    private ArrayList<TouristBooking> touristBookingArrayList;
    private Context context;



    public TouristBookingRecyclerAdapter(ArrayList<TouristBooking> touristBookingArrayList, Context context) {
        this.touristBookingArrayList = touristBookingArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TouristBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_booking, parent, false);

        return new TouristBookingRecyclerAdapter.TouristBookingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristBookingViewHolder holder, int position) {

        TouristBooking touristBooking = touristBookingArrayList.get(position);

        holder.tvDestinationName.setText(touristBooking.getDestination_name());
        holder.tvDestinationLocation.setText(touristBooking.getDestination_location());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(context, TouristBookingActivity.class);
                intent.putExtra("touristBooking", touristBooking);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return touristBookingArrayList.size();
    }

    public class TouristBookingViewHolder extends RecyclerView.ViewHolder{

        public TextView tvDestinationName, tvDestinationLocation;
        public View layout;

        public TouristBookingViewHolder(View view){
            super(view);
            tvDestinationName = view.findViewById(R.id.tv_trip_booking_name);
            tvDestinationLocation = view.findViewById(R.id.tv_trip_booking_destination);

            layout = view;
        }
    }
}
