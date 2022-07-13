package com.example.formulaone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulaone.R;
import com.example.formulaone.apiservices.DriverDataService;
import com.example.formulaone.apiservices.RaceDataService;
import com.example.formulaone.models.Driver;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.SharingHelper;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.BranchEvent;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import io.branch.referral.util.ShareSheetStyle;

public class DriverProfileFragment extends Fragment {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    TextView driverName, championshipStanding, totalPoints, yearPodium,
            totalPodium, totalwc, placeOfBirth, nationality;
    ImageView shareButton, driverImage;
    String image;

    int mParam1;
    String code, firstName, lastName, driverID;
    private ArrayList<Driver> driverArrayList;
    DriverDataService driverDataService;


    BranchUniversalObject driverObject;

    public DriverProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            driverID = getArguments().getString("$referring_link");
            Log.i("$referring_link", String.valueOf(driverID));
            code = getArguments().getString("code");


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_profile, container, false);
        driverName = view.findViewById(R.id.driverName);
        championshipStanding = view.findViewById(R.id.championShipStanding);
        totalPoints = view.findViewById(R.id.totalPoints);
        yearPodium = view.findViewById(R.id.yearPodium);
        totalPodium = view.findViewById(R.id.totalYearPodium);
        totalwc = view.findViewById(R.id.totalwc);
        nationality = view.findViewById(R.id.mNationality);
        shareButton = view.findViewById(R.id.shareButton);
        driverImage = view.findViewById(R.id.driverImageLarge);





        //firstName = getArguments().getString("name");
        //lastName = getArguments().getString("lastName");
        driverID = getArguments().getString("driverId");
        //Toast.makeText(getContext(), String.valueOf(driverID), Toast.LENGTH_SHORT).show();

        driverDataService = new DriverDataService(getContext());
        driverDataService.driverById(driverID, new DriverDataService.DriverDetailsResponseListener() {
            @Override
            public void onResponse(Driver driver) {
                Toast.makeText(getContext(), String.valueOf(driver.getDriverId()), Toast.LENGTH_SHORT).show();
                driverName.setText(driver.getDriverName() + " " + driver.getLastName());
                nationality.setText(driver.getNationality());
                Toast.makeText(getContext(), "In fragment" + driver.getNationality(), Toast.LENGTH_SHORT).show();
                String driverCode = driver.getDriverCode();
                Toast.makeText(getContext(), "In fragment" + driverCode, Toast.LENGTH_SHORT).show();
                DatabaseReference getImage = databaseReference.child("SizedImage").child(driverCode+"L");
                getImage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        image = snapshot.getValue(String.class);
                        Toast.makeText(getContext(), "In fragment" + image, Toast.LENGTH_SHORT).show();
                        Picasso.get()
                                .load(image)
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(driverImage, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get()
                                                .load(image)
                                                .into(driverImage);
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });



            }

            @Override
            public void onError(String message) {
                Log.i("Error", message);
            }
        });



        //Log.i("DriverProfileFragment", "Code: " + code + " First Name: " + firstName + " Last Name: " + lastName);
        driverName.setText(firstName + " " + lastName);
        Bundle bundle = new Bundle();

        //bundle.getSerializable("driverArrayList");
        //Toast.makeText(getContext(), "Driver Array List: " + driverArrayList.toString(), Toast.LENGTH_SHORT).show();
        //championshipStanding.setText(getArguments().getString("championshipStanding"));
        //totalPoints.setText(getArguments().getString("totalPoints"));
        //yearPodium.setText(getArguments().getString("yearPodium"));
        //totalPodium.setText(getArguments().getString("totalPodium"));
        //totalwc.setText(getArguments().getString("totalwc"));
        //placeOfBirth.setText(getArguments().getString("placeOfBirth"));
        nationality.setText(getArguments().getString("nationality"));


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDriverProfile();

                BranchUniversalObject buo = new BranchUniversalObject()
                        .setCanonicalIdentifier("Profile Share: " + driverID)
                        .setTitle(getArguments().getString("name")+ " "+getArguments().getString("lastName"))
                        .setContentImageUrl(image)
                        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
                        .setContentDescription("Driver Profile link shared")
                        .setContentMetadata(new ContentMetadata()
                                .addCustomMetadata("driverId", driverID));

                //Profile sharing event
                new BranchEvent(BRANCH_STANDARD_EVENT.SHARE)
                        .addContentItems(buo)
                        .logEvent(getContext());
            }
        });





        return view;
    }

    private void shareProfileEvent() {


    }

    private void shareDriverProfile() {

        LinkProperties linkProperties = new LinkProperties()
                .setFeature("DriverProfileSharing")
                .setStage("1")
                .addControlParameter("$android_deeplink_path", "driverProfile")
                .addControlParameter("driverId", driverID);

        final String driverName = getArguments().getString("name") + " " + getArguments().getString("lastName");
        String shareTitle = "Check out my favorite driver " + driverName;
        String shareMessage = "Check out my favorite driver " + driverName + " on Formula One App. Download the app now!";
        String copyUrlMessage = "Save "+driverName+"'s profile url";
        String copiedUrlMessage = "Copied to clipboard";

        ShareSheetStyle shareSheetStyle = new ShareSheetStyle(getContext(), shareTitle, shareMessage)
                .setCopyUrlStyle(getResources().getDrawable(android.R.drawable.ic_menu_send),copyUrlMessage, copiedUrlMessage)
                .setMoreOptionStyle(getResources().getDrawable(android.R.drawable.ic_menu_search), "More")
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.TWITTER)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.WHATS_APP)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE);

        BranchUniversalObject branchUniversalObject = new BranchUniversalObject()
                .setCanonicalIdentifier("driverProfile")
                .setTitle(driverName)
                .setContentDescription("Check out this driver")
                //.setContentImageUrl(getDriverImageUrl())
                .setContentMetadata(new ContentMetadata()
                        .addCustomMetadata("driverId", driverID));


        branchUniversalObject.showShareSheet(getActivity(), linkProperties, shareSheetStyle, new Branch.BranchLinkShareListener() {
            @Override
            public void onShareLinkDialogLaunched() {
                Log.i("DriverProfileFragment", "Share Link Dialog Launched");
            }

            @Override
            public void onShareLinkDialogDismissed() {
                Log.i("DriverProfileFragment", "Share Link Dialog Dismissed");
            }

            @Override
            public void onLinkShareResponse(String sharedLink, String sharedChannel, BranchError error) {
                Log.i("DriverProfileFragment", "Link Share Response: " + sharedLink);
            }

            @Override
            public void onChannelSelected(String channelName) {
                Log.i("DriverProfileFragment", "Channel Selected: " + channelName);
            }
        });

    }
}