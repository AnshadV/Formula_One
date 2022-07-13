package com.example.formulaone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.models.ConstructorStanding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ConstructorStandingAdapter extends FirebaseRecyclerAdapter<ConstructorStanding, ConstructorStandingAdapter.ConstructorStandingHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ConstructorStandingAdapter(@NonNull FirebaseRecyclerOptions<ConstructorStanding> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ConstructorStandingHolder holder, int position, @NonNull ConstructorStanding model) {
        holder.name.setText(model.getName());
        holder.points.setText(model.getPoints());
        String link = model.getLink();
        Picasso.get()
                .load(link)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(link)
                                .into(holder.image);
                    }
                });
    }

    @NonNull
    @Override
    public ConstructorStandingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.constructor_standing_item, parent, false);
        return new ConstructorStandingHolder(view);
    }

    public class ConstructorStandingHolder extends RecyclerView.ViewHolder {
        TextView name, points, position;
        ImageView image;
        public ConstructorStandingHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.sConstructorName);
            points = itemView.findViewById(R.id.sConstructorPoints);
            image = itemView.findViewById(R.id.sConstrutorImage);



        }
    }
}
