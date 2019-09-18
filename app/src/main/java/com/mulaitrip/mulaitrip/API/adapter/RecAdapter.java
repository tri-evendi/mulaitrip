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
import com.mulaitrip.mulaitrip.API.model.Places;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.PlacesDetailActivity;

import java.util.List;

/**
 * Created by Master on 19/07/2019.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder>{

    private List<Places> recList;
    private Context context;

    public RecAdapter(List<Places> recList, Context context) {
        this.recList = recList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecAdapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideitem02, parent, false);
        return new RecAdapter.RecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.RecViewHolder holder,final int position) {

        Places model = recList.get(position);
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .fitCenter()
                .into(holder.image);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
//        holder.avgrating.setText(model.getAvgrating());
        holder.places_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlacesDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", recList.get(position).getId());
                intent.putExtra("name", recList.get(position).getName());
                intent.putExtra("googleID", recList.get(position).getGoogleID());
                intent.putExtra("latitude", recList.get(position).getLatitude());
                intent.putExtra("longitude", recList.get(position).getLongitude());
                intent.putExtra("address", recList.get(position).getAddress());
                intent.putExtra("category", recList.get(position).getCategory());
                intent.putExtra("image", recList.get(position).getImage());
                intent.putExtra("description", recList.get(position).getDescription());
                intent.putExtra("avgrating", recList.get(position).getAvgrating());
                intent.putExtra("website", recList.get(position).getWebsite());
                intent.putExtra("phone", recList.get(position).getPhone());
                intent.putExtra("price", recList.get(position).getPrice());
                intent.putExtra("status", recList.get(position).getStatus());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recList.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, avgrating;
        ImageView image;
        LinearLayout places_layout;

        public RecViewHolder(@NonNull View v) {
            super(v);
            places_layout = v.findViewById(R.id.places_layout_slideitem);
            name =  v.findViewById(R.id.name);
            price =  v.findViewById(R.id.price);
            avgrating =  v.findViewById(R.id.avgratings);
            image =  v.findViewById(R.id.image);
        }
    }
}
