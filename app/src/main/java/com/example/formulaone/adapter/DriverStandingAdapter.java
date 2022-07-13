package com.example.formulaone.adapter;

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

public class DriverStandingAdapter extends FirebaseRecyclerAdapter<DriverStanding, DriverStandingAdapter.DriverStandingAdapterHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DriverStandingAdapter(@NonNull FirebaseRecyclerOptions<DriverStanding> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DriverStandingAdapterHolder holder, int position, @NonNull DriverStanding model) {
        holder.firstName.setText(model.getFirstName());
        holder.lastName.setText(model.getLastName());
        holder.DriverPosition.setText(model.getPosition());
        holder.points.setText(model.getPoints());
        holder.constructor.setText(model.getConstructor());
        String imageUrl = model.getLink();
        Picasso.get()
                .load(imageUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.driverImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(imageUrl)
                                .into(holder.driverImage);
                    }
                });

    }

    @NonNull
    @Override
    public DriverStandingAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_standing_item, parent, false);
        return new DriverStandingAdapterHolder(view);
    }

    public class DriverStandingAdapterHolder extends RecyclerView.ViewHolder {
        TextView DriverPosition, firstName, lastName, points, constructor;
        ImageView driverImage;


        public DriverStandingAdapterHolder(@NonNull View itemView) {
            super(itemView);

            DriverPosition = itemView.findViewById(R.id.standingDot);
            firstName = itemView.findViewById(R.id.standingDriverFirstName);
            lastName = itemView.findViewById(R.id.standingDriverLastName);
            constructor = itemView.findViewById(R.id.standingConstructorName);
            points = itemView.findViewById(R.id.standingPoints);
            driverImage = itemView.findViewById(R.id.standingDriverImage);

        }
    }
}
