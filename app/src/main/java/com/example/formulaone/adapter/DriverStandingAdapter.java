package com.example.formulaone.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.models.DriverStanding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DriverStandingAdapter extends RecyclerView.Adapter<DriverStandingAdapter.RecyclerViewHolder> {


    private ArrayList<DriverStanding> driverStandingList;
    private Context context;

    public DriverStandingAdapter(ArrayList<DriverStanding> driverStandingList, Context context) {
        this.context = context;
        this.driverStandingList = driverStandingList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_standing_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        DriverStanding driverStanding = driverStandingList.get(position);
        holder.name.setText(driverStanding.getDriverName());
        holder.position.setText(String.valueOf(driverStanding.getPosition()));
        holder.points.setText(String.valueOf(driverStanding.getPoints()));
        holder.constructor.setText(driverStanding.getConstructor());
    }


    @Override
    public int getItemCount() {
        return driverStandingList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, position, points, constructor;
        private ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.standingDriverName);
            position = itemView.findViewById(R.id.standingDot);
            imageView = itemView.findViewById(R.id.driverImage);
            points = itemView.findViewById(R.id.standingPoints);
            constructor = itemView.findViewById(R.id.standingConstructorName);


        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            int position = getAdapterPosition();
            Bundle bundle = new Bundle();


        }
    }
}
