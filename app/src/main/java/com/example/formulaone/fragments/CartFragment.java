package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaone.R;
import com.example.formulaone.adapter.CartAdapter;
import com.example.formulaone.models.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    RecyclerView productList;
    CartAdapter cartAdapter;
    DatabaseReference mbase;
    Query query;
    Button checkoutButton, Button22;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        productList = view.findViewById(R.id.cartItemsRecyclerView);
        TextView subTotalPrice = view.findViewById(R.id.subTotalValue);
        TextView totalPriceTv = view.findViewById(R.id.totalValue);
        Button22 = view.findViewById(R.id.button22);

        String constructorId = getArguments().getString("constructorId");

        //get all the child values of Cart and pass it to the adapter
        mbase = FirebaseDatabase.getInstance().getReference("Cart");
        query = mbase.orderByChild("constructor").equalTo("ferrari");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productList.setLayoutManager(linearLayoutManager);
        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(query, Product.class)
                .build();
        //Toast.makeText(getContext(), ""+options.getSnapshots().size(), Toast.LENGTH_SHORT).show();
        cartAdapter = new CartAdapter(options);
        productList.setAdapter(cartAdapter);

        DatabaseReference totalPrice = FirebaseDatabase.getInstance().getReference("Cart");
        //find the total sum of all childvalues of "price" and pass it to the textview
        Query totalPriceQuery = totalPrice.orderByChild("price").equalTo("price");
        //Toast.makeText(getContext(), "TT"+ totalPriceQuery, Toast.LENGTH_SHORT).show();

        Button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BranchUniversalObject buo = new BranchUniversalObject()
                        .setCanonicalIdentifier("purchase/"+constructorId)
                        .setTitle("Purchase")
                        .setPrice(100, CurrencyType.USD)
                        .setContentDescription("Purchase from "+ constructorId +" merchandise")

                        .setContentMetadata(new ContentMetadata()
                                .addCustomMetadata("constructor", constructorId)
                        );

                new BranchEvent("TEST_CUSTOM")
                        .setCustomerEventAlias("First Purchase")
                        .addContentItems(buo)
                        .setCustomerEventAlias("CC")
                        .setRevenue(100)
                        //.setTransactionID()
                        .logEvent(getContext());
            }
        });
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BranchUniversalObject buo = new BranchUniversalObject()
                        .setCanonicalIdentifier("purchase/"+constructorId)
                        .setTitle("Purchase")
                        .setPrice(100, CurrencyType.USD)
                        .setContentDescription("Purchase from "+ constructorId +" merchandise")

                        .setContentMetadata(new ContentMetadata()
                                .addCustomMetadata("constructor", constructorId)
                        );

                new BranchEvent("TEST_CUSTOM")
                        .setCustomerEventAlias("First Purchase")
                        .addContentItems(buo)
                        .setCustomerEventAlias("CC")
                        .setRevenue(100)
                        .logEvent(getContext());
            }
        });
        return view;



    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }
}