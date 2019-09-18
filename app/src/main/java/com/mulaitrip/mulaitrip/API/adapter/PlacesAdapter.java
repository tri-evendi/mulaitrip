package com.mulaitrip.mulaitrip.API.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.API.model.Places;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.PlacesDetailActivity;

import java.util.List;

/**
 * Created by Master on 03-May-19.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {
    private List<Places> placesList;
    private Context context;

    public PlacesAdapter(List<Places> placesList, Context context) {
        this.placesList = placesList;
        this.context = context;
    }

    @Override
    public PlacesAdapter.PlacesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item01, parent, false);
        return new PlacesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PlacesAdapter.PlacesViewHolder holder, final int position) {
        Places model = placesList.get(position);
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .fitCenter()
                .into(holder.image);
        holder.name.setText(String.valueOf(model.getName()));
        holder.address.setText(String.valueOf(model.getAddress()));
        holder.avgrating.setText(String.valueOf(model.getAvgrating()));
        holder.status.setText(String.valueOf(model.getStatus()));
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.places_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlacesDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", placesList.get(position).getId());
                intent.putExtra("detail_url", placesList.get(position).getDetail_url());
                intent.putExtra("category_url", placesList.get(position).getCategory_url());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public static class PlacesViewHolder extends RecyclerView.ViewHolder {

        public TextView name, address, avgrating, status, price;
        public ImageView image;
        LinearLayout places_layout;


        public PlacesViewHolder(View v) {
            super(v);
            places_layout = v.findViewById(R.id.places_layout_item);
            name = v.findViewById(R.id.name);
            address =  v.findViewById(R.id.address);
            avgrating =  v.findViewById(R.id.avgrating);
            status =  v.findViewById(R.id.status);
            price =  v.findViewById(R.id.price);
            image =  v.findViewById(R.id.image);
        }
    }
}
