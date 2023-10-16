package com.example.formulaone.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.formulaone.R;

import org.json.JSONObject;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RaceFragment newInstance(String param1, String param2) {
        RaceFragment fragment = new RaceFragment();
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
        String userId = "ABC12";
        View view = inflater.inflate(R.layout.fragment_race, container, false);

        Button button = view.findViewById(R.id.buttonUpdate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Branch.getInstance().setIdentity(userId, new Branch.BranchReferralInitListener() {
                    @Override
                    public void onInitFinished(@Nullable JSONObject referringParams, @Nullable BranchError error) {
                        Log.d("BranchSDK_Tester", "Identity set to " + userId +"\nInstall params = " + referringParams.toString());
                        if (error != null) {
                            Log.e("BranchSDK_Tester", "branch set Identity failed. Caused by -" + error.getMessage());
                        }
                        Toast.makeText(getContext(), "Set Identity to " + userId, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }
}