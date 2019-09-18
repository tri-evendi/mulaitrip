package com.mulaitrip.mulaitrip.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mulaitrip.mulaitrip.R;


public class PlaceReviewDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    public PlaceReviewDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_place_review_detail, container, false);

        Toolbar actionBar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(actionBar);
        ((AppCompatActivity)getActivity()).setTitle("Review");
        setHasOptionsMenu(true);
        actionBar.setTitleTextColor(0xFFFFFFFF);

        return rootView;
    }
}
