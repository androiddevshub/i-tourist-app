package com.example.itouristapp.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Review;

import java.util.ArrayList;

public class TourGuideReviewsRecyclerAdapter extends RecyclerView.Adapter<TourGuideReviewsRecyclerAdapter.TourGuideReviewsViewHolder> {


    private ArrayList<Review> reviewArrayList;
    private Context context;

    public TourGuideReviewsRecyclerAdapter(ArrayList<Review> reviewArrayList, Context context) {
        this.reviewArrayList = reviewArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TourGuideReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_tour_guide_review, parent, false);

        return new TourGuideReviewsRecyclerAdapter.TourGuideReviewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TourGuideReviewsViewHolder holder, int position) {

        Review review = reviewArrayList.get(position);

        holder.tvReviewUserName.setText(review.getUser_name());
        holder.tvReviewDescription.setText(review.getDescription());

    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    public class TourGuideReviewsViewHolder extends RecyclerView.ViewHolder{

        public TextView tvReviewUserName, tvReviewDescription;
        public View layout;


        public TourGuideReviewsViewHolder(View view){
            super(view);
            tvReviewUserName = view.findViewById(R.id.tv_review_user_name);
            tvReviewDescription = view.findViewById(R.id.tv_review_description);
            layout = view;
        }
    }
}
