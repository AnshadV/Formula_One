package com.example.formulaone.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.fragments.ConstructorProfileFragment;
import com.example.formulaone.models.Constructor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ConstructorAdapter extends RecyclerView.Adapter<ConstructorAdapter.RecyclerViewHolder> {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ArrayList<Constructor> constructorArrayList;
    private Context context;

    public ConstructorAdapter(ArrayList<Constructor> constructorArrayList, Context context){
        this.constructorArrayList = constructorArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.constructor_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        databaseReference.keepSynced(true);
        Constructor model = constructorArrayList.get(position);
        //holder.constructorId.setText(model.getConstructorId());
        holder.name.setText(model.getName());
        holder.nationality.setText(model.getNationality());
        //holder.url.setText(model.getUrl());
        DatabaseReference getImage = databaseReference.child(model.getConstructorId());
        getImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image = dataSnapshot.getValue(String.class);
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("constructorId", model.getConstructorId());
                Toast.makeText(context, "Constructor Id: " + model.getConstructorId(), Toast.LENGTH_SHORT).show();
                ConstructorProfileFragment constructorProfileFragment = new ConstructorProfileFragment();
                constructorProfileFragment.setArguments(bundle);

                Navigation.findNavController(view).navigate(R.id.action_constructorsFragment_to_constructorProfileFragment, bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return constructorArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView constructorId, name, url, nationality;
        private ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            //constructorId = itemView.findViewById(R.id.constructorID);
            name = itemView.findViewById(R.id.name);
            nationality = itemView.findViewById(R.id.nationality);

            //url = itemView.findViewById(R.id.url);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
