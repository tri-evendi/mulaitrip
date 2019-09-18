package com.mulaitrip.mulaitrip.API.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.API.model.Restaurants;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.PlacesDetailActivity;

import java.util.List;

/**
 * Created by Master on 19/07/2019.
 */

public class RestAdapter extends RecyclerView.Adapter<RestAdapter.RestViewHolder>{

    private List<Restaurants> restList;
    private Context context;

    public RestAdapter(List<Restaurants> restList, Context context) {
        this.restList = restList;
        this.context = context;
    }
    @NonNull
    @Override
    public RestAdapter.RestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item01, parent, false);
        return new RestAdapter.RestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestAdapter.RestViewHolder holder,final int position) {
        Restaurants model = restList.get(position);
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .fitCenter()
                .into(holder.image);
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.avgrating.setText(String.valueOf(model.getAvgrating()));
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.places_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlacesDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", restList.get(position).getId());
                intent.putExtra("name", restList.get(position).getName());
                intent.putExtra("googleID", restList.get(position).getGoogleID());
                intent.putExtra("latitude", restList.get(position).getLatitude());
                intent.putExtra("longitude", restList.get(position).getLongitude());
                intent.putExtra("address", restList.get(position).getAddress());
                intent.putExtra("image", restList.get(position).getImage());
                intent.putExtra("website", restList.get(position).getWebsite());
                intent.putExtra("phone", restList.get(position).getPhone());
                intent.putExtra("avgrating", restList.get(position).getAvgrating());
                intent.putExtra("price", restList.get(position).getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restList.size();
    }

    public class RestViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, avgrating, price;
        public ImageView image;
        LinearLayout places_layout;


        public RestViewHolder(View v) {
            super(v);
            places_layout = v.findViewById(R.id.places_layout_item);
            name = v.findViewById(R.id.name);
            address =  v.findViewById(R.id.address);
            price =  v.findViewById(R.id.price);
            avgrating =  v.findViewById(R.id.avgrating);
            image =  v.findViewById(R.id.image);
        }
    }
}
