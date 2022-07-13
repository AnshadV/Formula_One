package com.example.formulaone.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.fragments.DriverProfileFragment;
import com.example.formulaone.models.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;

public class DriverAdapter extends  RecyclerView.Adapter<DriverAdapter.RecyclerViewHolder>{
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();



    private ArrayList<Driver> driverArrayList;
    private Context context;

    public DriverAdapter(ArrayList<Driver> driverArrayList, Context context) {
        this.context = context;
        this.driverArrayList = driverArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final int mPosition = holder.getAdapterPosition();

        databaseReference.keepSynced(true);
        Driver model = driverArrayList.get(position);
        holder.firstName.setText(model.getDriverName());
        holder.lastName.setText(model.getLastName());
        holder.nationality.setText(model.getNationality());
        holder.code.setText(model.getDriverCode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DriverAdapter", "Position: " + mPosition);
                Bundle bundle = new Bundle();
                bundle.putInt("position", mPosition);
                bundle.putString("driverId", model.getDriverId());
                bundle.putString("name", model.getDriverName());
                bundle.putString("lastName", model.getLastName());
                bundle.putString("nationality", model.getNationality());
                bundle.putString("code", model.getDriverCode());
                //bundle.putParcelableArrayList("driverArray", (ArrayList<? extends Parcelable>) driverArrayList);

                profileVisitEvent(model);

                DriverProfileFragment profileFragment = new DriverProfileFragment();
                profileFragment.setArguments(bundle);

                Navigation.findNavController(view).navigate(R.id.action_driversFragment_to_driverProfileFragment, bundle);


            }
        });
        DatabaseReference getImage = databaseReference.child(model.getDriverCode());
        getImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.getValue(String.class);
                Picasso.get()
                        .load(image)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(image)
                                        .into(holder.imageView);
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    //Profile Visit Event
    private void profileVisitEvent(Driver model) {
        BranchUniversalObject buo = new BranchUniversalObject()
                .setCanonicalIdentifier("item" +model.getDriverId())
                .setTitle(model.getDriverName() + " " + model.getLastName())
                .setContentImageUrl(model.getDriverImageUrl())
                .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                .setContentDescription("Driver Profile")
                .setContentMetadata(new ContentMetadata()
                        .addCustomMetadata("driverId", model.getDriverId())
                        .addCustomMetadata("name", model.getDriverName() + " " + model.getLastName()));

                new BranchEvent(BRANCH_STANDARD_EVENT.VIEW_ITEM)
                        .addContentItems(buo)
                        .logEvent(context);


    }

    @Override
    public int getItemCount() {
        return driverArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView firstName, lastName, code, nationality;
        private ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            nationality = itemView.findViewById(R.id.driverNationality);
            imageView = itemView.findViewById(R.id.driverImage);
            code = itemView.findViewById(R.id.driverCode);


        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            int position = getAdapterPosition();
            Bundle bundle = new Bundle();


        }
    }


}
