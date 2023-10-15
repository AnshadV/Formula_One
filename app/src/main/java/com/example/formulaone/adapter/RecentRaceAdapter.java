package com.example.formulaone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.models.RecentRace;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecentRaceAdapter extends FirebaseRecyclerAdapter<RecentRace, RecentRaceAdapter.RecentRaceAdapterHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecentRaceAdapter(@NonNull FirebaseRecyclerOptions<RecentRace> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecentRaceAdapterHolder holder, int position, @NonNull RecentRace model) {
        holder.firstName.setText(model.getFirstName());
        //holder.lastName.setText(model.getLastName());
        holder.raceStanding.setText(model.getPosition());
        holder.raceTime.setText(model.getTime());
    }

    @NonNull
    @Override
    public RecentRaceAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.race_result_item, parent, false);
        return new RecentRaceAdapterHolder(view);
    }

    public class RecentRaceAdapterHolder extends RecyclerView.ViewHolder {
        TextView raceStanding, firstName, lastName, raceTime;
        public RecentRaceAdapterHolder(@NonNull View itemView) {
            super(itemView);
            raceStanding = itemView.findViewById(R.id.race_standing);
            firstName = itemView.findViewById(R.id.resultDriverName);
            //lastName = itemView.findViewById(R.id.lastName);
            raceTime = itemView.findViewById(R.id.race_time);

        }
    }

}