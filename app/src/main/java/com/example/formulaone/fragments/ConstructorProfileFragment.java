package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaone.R;
import com.example.formulaone.adapter.ProductAdapter;
import com.example.formulaone.models.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConstructorProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorProfileFragment extends Fragment {
    RecyclerView productList;
    ProductAdapter productAdapter;
    DatabaseReference mbase, cartdb, constructordb;
    Query query;
    Button addButton, gotoCart;
    TextView chassis, powerUnit, teamChief, technicalChief, firstGP, WC, polePostions, fastestLaps, base;
    ImageView constructorImage;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConstructorProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConstructorProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConstructorProfileFragment newInstance(String param1, String param2) {
        ConstructorProfileFragment fragment = new ConstructorProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_constructor_profile, container, false);
       productList = view.findViewById(R.id.productRecyclerView);
       gotoCart = view.findViewById(R.id.GoToCart);

       Bundle bundle = getArguments();
       String constructor = bundle.getString("constructorId");
       Log.d("In Con Profile", bundle.getString("constructorId").toString());

       mbase = FirebaseDatabase.getInstance().getReference("Products");
       query = mbase.orderByChild("constructor").equalTo(constructor);

        //set orienation to horizontal
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        productList.setLayoutManager(linearLayoutManager);
        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();
        productAdapter = new ProductAdapter(options);
        productList.setAdapter(productAdapter);

        cartdb = FirebaseDatabase.getInstance().getReference("Cart");

        //check the child count of cartdb
        cartdb.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    gotoCart.setVisibility(View.VISIBLE);
                }
                else{
                    gotoCart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        constructordb = FirebaseDatabase.getInstance().getReference("constructors");
        constructordb.child(constructor).addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                chassis = view.findViewById(R.id.chassis);
                powerUnit = view.findViewById(R.id.powerUnit);
                teamChief = view.findViewById(R.id.teamChief);
                technicalChief = view.findViewById(R.id.technicalChief);
                firstGP = view.findViewById(R.id.firstGP);
                WC = view.findViewById(R.id.worldChampionships);
                polePostions = view.findViewById(R.id.polePositions);
                fastestLaps = view.findViewById(R.id.fastestLaps);
                base = view.findViewById(R.id.constructorBase);
                constructorImage = view.findViewById(R.id.constructorImage);
                chassis.setText(dataSnapshot.child("chassis").getValue().toString());
                powerUnit.setText(dataSnapshot.child("powerUnit").getValue().toString());
                teamChief.setText(dataSnapshot.child("teamChief").getValue().toString());
                technicalChief.setText(dataSnapshot.child("technicalChief").getValue().toString());
                firstGP.setText(dataSnapshot.child("firstGP").getValue().toString());
                WC.setText(dataSnapshot.child("worldChampionships").getValue().toString());
                polePostions.setText(dataSnapshot.child("polePositions").getValue().toString());
                fastestLaps.setText(dataSnapshot.child("fastestLaps").getValue().toString());
                base.setText(dataSnapshot.child("base").getValue().toString());
                Picasso.get()
                        .load(dataSnapshot.child("image").getValue().toString())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(constructorImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError(Exception e) {
                                Picasso.get()
                                        .load(dataSnapshot.child("image").getValue().toString())
                                        .into(constructorImage);
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    BranchUniversalObject buo = new BranchUniversalObject()
                            .setCanonicalIdentifier("purchase")
                            .setTitle("Purchase")
                            .setPrice(100, CurrencyType.USD)
                            .setContentDescription("Purchase from  merchandise")

                            .setContentMetadata(new ContentMetadata()
                                    .addCustomMetadata("constructor", "cu")
                            );

                    new BranchEvent(BRANCH_STANDARD_EVENT.PURCHASE)
                            .addContentItems(buo)
                            .setCustomerEventAlias("CL")
                            .setRevenue(120)
                            .logEvent(getContext());


                //Bundle bundle = new Bundle();
                //bundle.putString("constructorId", "ferrari");
                //Navigation.findNavController(v).navigate(R.id.action_constructorProfileFragment_to_cartFragment);
            }
        });





       return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }
}