package com.mulaitrip.mulaitrip.API.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.API.model.GooglePlaces;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.ListDetailActivity;

import java.util.List;

/**
 * Created by Master on 30-May-19.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context mContext;
    private List<GooglePlaces> gplaceList;

    public ListAdapter(Context mContext, List<GooglePlaces> gplaceList) {
        this.mContext = mContext;
        this.gplaceList = gplaceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item02, parent, false);
        return new ListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GooglePlaces gplace = gplaceList.get(position);
        holder.name.setText(gplace.getTpName());
        holder.address.setText(gplace.getTpAddress());
        Glide.with(mContext).load(gplace.getTpReference()).into(holder.imageViewTP1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ListDetailActivity.class);
                intent.putExtra("place_id", gplace.getTpPlaceID());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gplaceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewTP1;
        public TextView name, address;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewTP1 = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
        }
    }
}
