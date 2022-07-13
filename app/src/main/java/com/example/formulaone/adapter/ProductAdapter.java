package com.example.formulaone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formulaone.R;
import com.example.formulaone.models.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.ProductCategory;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product, ProductAdapter.ProductHolder> {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ArrayList<String> productArrayList;
    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductHolder holder, int position, @NonNull Product model) {
        String productId = getRef(position).getKey();
        holder.itemName.setText(model.getName());
        holder.ItemPrice.setText(model.getPrice().toString());
        String imgLink = model.getImage();
        Picasso.get().load(imgLink).into(holder.itemImage);
        final String key = getRef(position).getKey();
        holder.addToCart.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save the product id in the cart
                DatabaseReference cartRef = databaseReference.child("Cart").child(key);
                cartRef.setValue(model);

                addToCartEvent(model);
            }
        }));

    }

    //Add to cart Event
    private void addToCartEvent(Product model) {
        BranchUniversalObject buo = new BranchUniversalObject()
                .setCanonicalIdentifier("item_" + model.getName())
                .setTitle(model.getName())
                .setContentImageUrl(model.getImage())
                .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                .setContentMetadata(new ContentMetadata()
                        .setPrice(Double.valueOf(model.getPrice()), CurrencyType.USD)
                        .setProductName(model.getName())
                        .setProductCategory(ProductCategory.APPAREL_AND_ACCESSORIES));

        new BranchEvent(BRANCH_STANDARD_EVENT.ADD_TO_CART)
                .setCurrency(CurrencyType.USD)
                .addContentItems(buo)
                .logEvent(context);



    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    public class ProductHolder  extends RecyclerView.ViewHolder {
        TextView itemName, ItemPrice;
        ImageView itemImage;
        Button addToCart;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            ItemPrice = itemView.findViewById(R.id.price);
            itemImage = itemView.findViewById(R.id.ItemImage);
            addToCart = itemView.findViewById(R.id.btn_add);

        }
    }

}
