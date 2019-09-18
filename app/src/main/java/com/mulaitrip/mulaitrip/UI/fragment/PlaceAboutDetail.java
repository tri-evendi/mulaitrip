package com.mulaitrip.mulaitrip.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.adapter.DetailPlacesAdapter;
import com.mulaitrip.mulaitrip.API.model.DetailPlaces;
import com.mulaitrip.mulaitrip.API.model.GetDetailPlaces;
import com.mulaitrip.mulaitrip.API.model.Places;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlaceAboutDetail extends Fragment {

    Places mPlaces = new Places();
    TextView id, name, googleID, latitude, longitude, address, category, type, description, avgrating, website, phone, price, status;
    ImageView image;
    RecyclerView rvdetailplaces;
    private LinearLayoutManager linearLayoutManager;
    private List<DetailPlaces> detailplacesList = new ArrayList<>();
    private DetailPlacesAdapter detailPlacesAdapter;
    private static String URL = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_about_detail, container, false);

        mPlaces.setDetail_url(getActivity().getIntent().getStringExtra("detail_url"));
        URL = String.valueOf((getActivity().getIntent().getStringExtra("detail_url")));

        //RecycleHorizontal
        rvdetailplaces = view.findViewById(R.id.rvdetailabout);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvdetailplaces.setLayoutManager(linearLayoutManager);
        rvdetailplaces.setItemAnimator(new DefaultItemAnimator());
        rvdetailplaces.setHasFixedSize(true);
        rvdetailplaces.setNestedScrollingEnabled(false);
        rvdetailplaces.setAdapter(detailPlacesAdapter);

        loadDetailPlaces();



//        name = view.findViewById(R.id.name);
//        address = view.findViewById(R.id.address);
//        description = view.findViewById(R.id.description);
//        status = view.findViewById(R.id.status);
//        website = view.findViewById(R.id.website);
//        phone = view.findViewById(R.id.phone);
//        image = view.findViewById(R.id.image);

//
//        name.setText(getActivity().getIntent().getStringExtra("name"));
//        address.setText(getActivity().getIntent().getStringExtra("address"));
//        description.setText(getActivity().getIntent().getStringExtra("description"));
//        status.setText(getActivity().getIntent().getStringExtra("status"));
//        website.setText(getActivity().getIntent().getStringExtra("website"));
//        phone.setText(getActivity().getIntent().getStringExtra("phone"));
//        Picasso.get().load(getActivity().getIntent().getStringExtra("image")).fit().into(image);
//

//        mPlaces.setName(getActivity().getIntent().getStringExtra("name"));
//        mPlaces.setAddress(getActivity().getIntent().getStringExtra("address"));
//        mPlaces.setDescription(getActivity().getIntent().getStringExtra("description"));
//        mPlaces.setStatus(getActivity().getIntent().getStringExtra("status"));
//        mPlaces.setWebsite(getActivity().getIntent().getStringExtra("website"));
//        mPlaces.setPhone(getActivity().getIntent().getStringExtra("phone"));
//        mPlaces.setImage(getActivity().getIntent().getStringExtra("image"));

        return view;
    }

    private void loadDetailPlaces() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<GetDetailPlaces> call = apiInterface.getDetailPlaces("");
        call.enqueue(new Callback<GetDetailPlaces>() {
            @Override
            public void onResponse(Call<GetDetailPlaces> call, Response<GetDetailPlaces> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    if (!detailplacesList.isEmpty()) {
                        detailplacesList.clear();
                    }
                    List<DetailPlaces> detailplacesList = response.body().getResults();
                    Log.d("Mulaitrip Get", "Get data Detail: " + String.valueOf(detailplacesList.size()));
                    detailPlacesAdapter = new DetailPlacesAdapter(detailplacesList, getActivity());
                    rvdetailplaces.setAdapter(detailPlacesAdapter);
                    detailPlacesAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetDetailPlaces> call, Throwable t) {
                Log.d("Error Get Detail", t.getMessage());
            }
        });
    }
}
