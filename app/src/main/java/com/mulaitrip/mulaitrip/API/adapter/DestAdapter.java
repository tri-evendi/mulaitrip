package com.mulaitrip.mulaitrip.API.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.API.model.Destinations;
import com.mulaitrip.mulaitrip.R;

import java.util.List;

/**
 * Created by Master on 18-May-19.
 */

public class DestAdapter extends RecyclerView.Adapter<DestAdapter.DestViewHolder>{
    private List<Destinations> destList;
    private Context context;

    public DestAdapter(List<Destinations> destList, Context context) {
        this.destList = destList;
        this.context = context;
    }

    @Override
    public DestAdapter.DestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slideitem, parent, false);
        return new DestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DestAdapter.DestViewHolder holder, int position) {
        Destinations model = destList.get(position);
        Glide.with(context)
                .load(model.getImage())
                .centerCrop()
                .fitCenter()
                .into(holder.image);
        holder.city.setText(model.getCity());
    }

    @Override
    public int getItemCount() {
        return destList.size();
    }

    public class DestViewHolder extends RecyclerView.ViewHolder {
        TextView city;
        ImageView image;

        public DestViewHolder(View v) {
            super(v);
            city =  v.findViewById(R.id.city_name);
            image =  v.findViewById(R.id.img);
        }
    }
}
