package com.mulaitrip.mulaitrip.API.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.API.model.DetailPlaces;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.ReviewsActivity;

import java.util.List;

/**
 * Created by Master on 26/07/2019.
 */

public class DetailPlacesAdapter extends RecyclerView.Adapter<DetailPlacesAdapter.DetailPlacesViewHolder>  {
    private List<DetailPlaces> detailplacesList;
    private Context context;

    public DetailPlacesAdapter(List<DetailPlaces> detailplacesList, Context context) {
        this.detailplacesList = detailplacesList;
        this.context = context;
    }
    @NonNull
    @Override
    public DetailPlacesAdapter.DetailPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailplaces, parent, false);
        return new DetailPlacesAdapter.DetailPlacesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPlacesAdapter.DetailPlacesViewHolder holder, final int position) {
        DetailPlaces model = detailplacesList.get(position);
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
        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReviewsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("add_review_url", detailplacesList.get(position).getAdd_review_url());
                intent.putExtra("id", detailplacesList.get(position).getId());
                intent.putExtra("destinations", detailplacesList.get(position).getDestinations());
                intent.putExtra("name", detailplacesList.get(position).getName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return detailplacesList.size();
    }

    public class DetailPlacesViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, avgrating, status, price;
        public ImageView image;
        public Button btnReview;

        public DetailPlacesViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.dname);
            address =  v.findViewById(R.id.daddress);
            btnReview = v.findViewById(R.id.btnReview);
            avgrating =  v.findViewById(R.id.davgrating);
            status =  v.findViewById(R.id.dstatus);
            price =  v.findViewById(R.id.dprice);
            image =  v.findViewById(R.id.dimage);
        }
    }
}
