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
import com.mulaitrip.mulaitrip.API.adapter.HotelsAdapter;
import com.mulaitrip.mulaitrip.API.model.Destinations;
import com.mulaitrip.mulaitrip.API.model.GetDestinations;
import com.mulaitrip.mulaitrip.API.model.GetHotels;
import com.mulaitrip.mulaitrip.API.model.Hotels;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotelsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    private List<Hotels> hotelsList = new ArrayList<>();
    private List<Destinations> destList = new ArrayList<>();
    private HotelsAdapter hotelsAdapter;
    private DestAdapter mSlideAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);

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
        recyclerViewVertical = view.findViewById(R.id.hotels_recyclerview);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);
        recyclerViewVertical.setItemAnimator(new DefaultItemAnimator());
        recyclerViewVertical.setHasFixedSize(true);
        recyclerViewVertical.setNestedScrollingEnabled(false);
        recyclerViewVertical.setAdapter(hotelsAdapter);
        return view;
    }

    @Override
    public void onRefresh() {
        loadHotels();
        loadDest();
    }
    private void onLoadingSwipeRefresh(){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadHotels();
                loadDest();
            }
        });
    }

    private void loadHotels() {
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<GetHotels> call = apiInterface.getHotels();
        call.enqueue(new Callback<GetHotels>() {
            @Override
            public void onResponse(Call<GetHotels> call, Response<GetHotels> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {
                    if (!hotelsList.isEmpty()) {
                        hotelsList.clear();
                    }
                    List<Hotels> hotelsList = response.body().getResults();
                    Log.d("Mulaitrip Get", "Jumlah data Hotels: " + String.valueOf(hotelsList.size()));
                    hotelsAdapter = new HotelsAdapter(hotelsList, getActivity());
                    recyclerViewVertical.setAdapter(hotelsAdapter);
                    hotelsAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetHotels> call, Throwable t) {
                Log.d("Error Get Destinations", t.getMessage());
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
