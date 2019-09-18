package com.mulaitrip.mulaitrip.MENU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.adapter.DestAdapter;
import com.mulaitrip.mulaitrip.API.adapter.PlacesAdapter;
import com.mulaitrip.mulaitrip.API.adapter.RecAdapter;
import com.mulaitrip.mulaitrip.API.model.Destinations;
import com.mulaitrip.mulaitrip.API.model.GetDestinations;
import com.mulaitrip.mulaitrip.API.model.GetPlaces;
import com.mulaitrip.mulaitrip.API.model.Places;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.AppPrefences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TravelFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    RecyclerView rvrekomendasi;
    private static String token;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    private LinearLayoutManager linearLayoutManager3;
    private List<Places> placesList = new ArrayList<>();
    private List<Destinations> destList = new ArrayList<>();
    private List<Places> recList = new ArrayList<>();
    private PlacesAdapter placesAdapter;
    private DestAdapter mSlideAdapter;
    private RecAdapter mSlideAdapter2;
    private SwipeRefreshLayout swipeRefreshLayout;
    TextView tvrecommender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);

        token = AppPrefences.get(getActivity()).getString("token", "token");

        tvrecommender = view.findViewById(R.id.tvrecom);
        tvrecommender.setVisibility(View.GONE);

        //Swipe Refresh
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        onLoadingSwipeRefresh();

        //RecycleHorizontal
        recyclerViewHorizontal = view.findViewById(R.id.destinations_recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHorizontal.setLayoutManager(linearLayoutManager);
        recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHorizontal.setHasFixedSize(true);
        recyclerViewHorizontal.setNestedScrollingEnabled(false);
        recyclerViewHorizontal.setAdapter(mSlideAdapter);

        //RecycleRekomendasi
        rvrekomendasi = view.findViewById(R.id.rekomendasi_recyclerview);
        linearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvrekomendasi.setLayoutManager(linearLayoutManager3);
        rvrekomendasi.setItemAnimator(new DefaultItemAnimator());
        rvrekomendasi.setHasFixedSize(true);
        rvrekomendasi.setNestedScrollingEnabled(false);
        rvrekomendasi.setAdapter(mSlideAdapter2);

        //RecycleVertical
        recyclerViewVertical = view.findViewById(R.id.places_recyclerview);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);
        recyclerViewVertical.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setNestedScrollingEnabled(false);
        recyclerViewVertical.setAdapter(placesAdapter);

        return view;
    }

    private void loadDest() {
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<GetDestinations> call = apiInterface.getDestinations();
        call.enqueue(new Callback<GetDestinations>() {
            @Override
            public void onResponse(Call<GetDestinations> call, Response<GetDestinations> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    if (!destList.isEmpty()) {
                        destList.clear();
                    }
                    tvrecommender.setVisibility(View.GONE);
                    List<Destinations> destList = response.body().getResults();
                    Log.d("Mulaitrip Get", "Jumlah data Destinations: " + String.valueOf(destList.size()));
                    mSlideAdapter = new DestAdapter(destList, getActivity());
                    recyclerViewHorizontal.setAdapter(mSlideAdapter);
                    mSlideAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetDestinations> call, Throwable t) {
                Log.d("Error Get Destinations", t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

//    private void loadRecs() {
//        swipeRefreshLayout.setRefreshing(true);
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<GetRecs> call = apiInterface.getRecPlaces(token);
//        call.enqueue(new Callback<GetRecs>() {
//            @Override
//            public void onResponse(Call<GetRecs> call, Response<GetRecs> response) {
//                if (response.isSuccessful() && response.body().getResults() != null) {
//                    if (!recList.isEmpty()) {
//                        recList.clear();
//                    }
//                    tvrecommender.setVisibility(View.VISIBLE);
//                    List<Places> recList = response.body().getResults();
//                    Log.d("Mulaitrip Get", "Jumlah data Rekomendations: " + String.valueOf(recList.size()));
//                    mSlideAdapter2 = new RecAdapter(recList, getActivity());
//                    rvrekomendasi.setAdapter(mSlideAdapter2);
//                    mSlideAdapter2.notifyDataSetChanged();
//                    swipeRefreshLayout.setRefreshing(false);
//                }else {
//                    tvrecommender.setVisibility(View.GONE);
//                    swipeRefreshLayout.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetRecs> call, Throwable t) {
//                tvrecommender.setVisibility(View.GONE);
//                Log.d("Error Get Recommend", t.getMessage());
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//    }

    private void loadPlaces(){
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<GetPlaces> call = apiInterface.getPlaces();
        call.enqueue(new Callback<GetPlaces>() {
            @Override
            public void onResponse(Call<GetPlaces> call, Response<GetPlaces> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    if (!placesList.isEmpty()) {
                        placesList.clear();
                    }
                    List<Places> placesList = response.body().getResults();
                    Log.d("Mulaitrip Get", "Jumlah data Places: " + String.valueOf(placesList.size()));
                    placesAdapter = new PlacesAdapter(placesList, getActivity());
                    recyclerViewVertical.setAdapter(placesAdapter);
                    placesAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPlaces> call, Throwable t) {
                Log.d("Error Get Places", t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        loadDest();
//        loadRecs();
        loadPlaces();
    }
    private void onLoadingSwipeRefresh(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadDest();
//                loadRecs();
                loadPlaces();
            }
        });
    }
}
