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
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.adapter.DestAdapter;
import com.mulaitrip.mulaitrip.API.adapter.RestAdapter;
import com.mulaitrip.mulaitrip.API.model.Destinations;
import com.mulaitrip.mulaitrip.API.model.GetDestinations;
import com.mulaitrip.mulaitrip.API.model.GetRestaurants;
import com.mulaitrip.mulaitrip.API.model.Restaurants;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RestaurantsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    private List<Restaurants> restList = new ArrayList<>();
    private List<Destinations> destList = new ArrayList<>();
    private RestAdapter restAdapter;
    private DestAdapter mSlideAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);

//        //ActionBar
//        toolbar = view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).setTitle("Restaurant");
//        toolbar.setTitleTextColor(0xFFFFFFFF);

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

        //RecycleVertical
        recyclerViewVertical = view.findViewById(R.id.restaurants_recyclerview);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);
        recyclerViewVertical.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setNestedScrollingEnabled(false);
        recyclerViewVertical.setAdapter(restAdapter);
        return view;
    }

    private void onLoadingSwipeRefresh() {
        loadRestaurants();
        loadDest();
    }

    @Override
    public void onRefresh() {
        loadRestaurants();
        loadDest();
    }

    private void loadRestaurants() {
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<GetRestaurants> call = apiInterface.getRestaurants();
        call.enqueue(new Callback<GetRestaurants>() {
            @Override
            public void onResponse(Call<GetRestaurants> call, Response<GetRestaurants> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    if (!restList.isEmpty()) {
                        restList.clear();
                    }
                    List<Restaurants> restList = response.body().getResults();
                    Log.d("Mulaitrip Get", "Jumlah data Restaurants: " + String.valueOf(restList.size()));
                    restAdapter = new RestAdapter(restList, getActivity());
                    recyclerViewVertical.setAdapter(restAdapter);
                    restAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetRestaurants> call, Throwable t) {
                Log.d("Error Get Restaurants", t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
}
