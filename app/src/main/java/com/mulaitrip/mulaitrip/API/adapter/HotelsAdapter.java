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
import com.mulaitrip.mulaitrip.API.model.Hotels;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.PlacesDetailActivity;

import java.util.List;

/**
 * Created by Master on 19/07/2019.
 */

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder> {

    private List<Hotels> hotelsList;
    private Context context;

    public HotelsAdapter(List<Hotels> hotelsList, Context context) {
        this.hotelsList = hotelsList;
        this.context = context;
    }

    @NonNull
    @Override
    public HotelsAdapter.HotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item01, parent, false);
        return new HotelsAdapter.HotelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelsAdapter.HotelsViewHolder holder, final int position) {
        Hotels model = hotelsList.get(position);
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
                intent.putExtra("id", hotelsList.get(position).getId());
                intent.putExtra("name", hotelsList.get(position).getName());
                intent.putExtra("googleID", hotelsList.get(position).getGoogleID());
                intent.putExtra("latitude", hotelsList.get(position).getLatitude());
                intent.putExtra("longitude", hotelsList.get(position).getLongitude());
                intent.putExtra("address", hotelsList.get(position).getAddress());
                intent.putExtra("image", hotelsList.get(position).getImage());
                intent.putExtra("website", hotelsList.get(position).getWebsite());
                intent.putExtra("phone", hotelsList.get(position).getPhone());
                intent.putExtra("avgrating", hotelsList.get(position).getAvgrating());
                intent.putExtra("price", hotelsList.get(position).getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelsList.size();
    }

    public class HotelsViewHolder extends RecyclerView.ViewHolder{
        public TextView name, address, avgrating, status, price;
        public ImageView image;
        LinearLayout places_layout;


        public HotelsViewHolder(View v) {
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
