package com.mulaitrip.mulaitrip.API.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.model.SearchPlaces;
import com.mulaitrip.mulaitrip.R;

import java.util.ArrayList;

/**
 * Created by Master on 22/07/2019.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //Context of the activity
    private Context mContext;
    private ArrayList<SearchPlaces> mNearByPlaceArrayList = new ArrayList<>();

    public PlaceListAdapter(Context context, ArrayList<SearchPlaces> nearByPlaceArrayList) {
        mContext = context;
        mNearByPlaceArrayList = nearByPlaceArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceListAdapterViewHolder(LayoutInflater
                .from(mContext).inflate(R.layout.place_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {((PlaceListAdapterViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mNearByPlaceArrayList.size();
    }

    private class PlaceListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int mItemPosition;
        //reference of the views
        private TextView mPlaceDistanceTextView;
        private TextView mPlaceNameTextView;
        private TextView mPlaceAddressTextView;
        private TextView mPlaceOpenStatusTextView;
        private ImageView mLocationIcon;

        private PlaceListAdapterViewHolder(View itemView) {
            super(itemView);

            mPlaceNameTextView = (TextView) itemView.findViewById(R.id.place_name);
            mPlaceAddressTextView = (TextView) itemView.findViewById(R.id.place_address);
            mPlaceOpenStatusTextView = (TextView) itemView.findViewById(R.id.place_open_status);
            mLocationIcon = (ImageView) itemView.findViewById(R.id.location_icon);
            itemView.setOnClickListener(this);
        }

        private void bindView(int position) {
            mItemPosition = position;
            mPlaceNameTextView.setText(mNearByPlaceArrayList.get(mItemPosition).getPlaceName());
            mPlaceAddressTextView.setText(mNearByPlaceArrayList.get(mItemPosition).getPlaceAddress());
//            if (
//                mNearByPlaceArrayList.get(mItemPosition).getPlaceOpeningHourStatus().equals("true")) {
//                mPlaceOpenStatusTextView.setText("Open Now");
//            } else if (
//                    mNearByPlaceArrayList.get(mItemPosition).getPlaceOpeningHourStatus().equals("false")) {
//                mPlaceOpenStatusTextView.setText("Closed");
//            } else {
//                mPlaceOpenStatusTextView.setText("Status Not Available");
//            }
            mLocationIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.color_divider));
        }
        @Override
        public void onClick(View v) {

            String latitude =  mNearByPlaceArrayList.get(mItemPosition).getPlaceLatitude().toString();
            String longitude = mNearByPlaceArrayList.get(mItemPosition).getPlaceLongitude().toString();
            if (isNetworkAvailable()) {
                String uri = "google.navigation:q=" + latitude + "," + longitude + "";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                try
                {
                    mContext.startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        mContext.startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        Toast.makeText(mContext, "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }
            } else
                Snackbar.make(mLocationIcon, "Not Connected",
                        Snackbar.LENGTH_SHORT).show();
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        }
    }
}
