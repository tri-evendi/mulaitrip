package com.mulaitrip.mulaitrip.MENU;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mulaitrip.mulaitrip.API.adapter.DestAdapter;
import com.mulaitrip.mulaitrip.API.model.Destinations;
import com.mulaitrip.mulaitrip.API.model.GetDestinations;
import com.mulaitrip.mulaitrip.API.model.GooglePlaces;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UI.ListActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreFragment extends Fragment{
    private DestAdapter mSlideAdapter;
    private RecyclerView hRecyclerView;
    private List<Destinations> destList = new ArrayList<>();
    private RecyclerView.LayoutManager linearLayoutManager;
    LocationManager locationManager;
    String provider;
    double latitude, longitude;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST_CODE = 101;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3,linearLayout4, linearLayout5,linearLayout6, linearLayout7, linearLayout8,
            linearLayout9, linearLayout10, linearLayout11, linearLayout12, linearLayout13, linearLayout14,linearLayout15, linearLayout16;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        hRecyclerView = (RecyclerView) view.findViewById(R.id.rvslide);
        mSlideAdapter = new DestAdapter(destList, getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        hRecyclerView.setLayoutManager(linearLayoutManager);
        hRecyclerView.setItemAnimator(new DefaultItemAnimator());
        hRecyclerView.setHasFixedSize(true);
        hRecyclerView.setAdapter(mSlideAdapter);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) view.findViewById(R.id.linearLayout4);
        linearLayout5 = (LinearLayout) view.findViewById(R.id.linearLayout5);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.linearLayout6);
        linearLayout7 = (LinearLayout) view.findViewById(R.id.linearLayout7);
        linearLayout8 = (LinearLayout) view.findViewById(R.id.linearLayout8);
        linearLayout9 = (LinearLayout) view.findViewById(R.id.linearLayout9);
        linearLayout10 = (LinearLayout) view.findViewById(R.id.linearLayout10);
        linearLayout11 = (LinearLayout) view.findViewById(R.id.linearLayout11);
        linearLayout12 = (LinearLayout) view.findViewById(R.id.linearLayout12);
        linearLayout13 = (LinearLayout) view.findViewById(R.id.linearLayout13);
        linearLayout14 = (LinearLayout) view.findViewById(R.id.linearLayout14);
        linearLayout15 = (LinearLayout) view.findViewById(R.id.linearLayout15);
        linearLayout16 = (LinearLayout) view.findViewById(R.id.linearLayout16);

        getResultListDest();
        getLocation();
        return view;
    }

    private List<GooglePlaces> gplaceList = new ArrayList<>();
    private void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    latitude = currentLocation.getLatitude();
                    longitude = currentLocation.getLongitude();
                    linearLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "atm");
                            intent.putExtra("keyword", "atm");
                            startActivity(intent);
                        }
                    });
                    linearLayout2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "airport");
                            intent.putExtra("keyword", "bandara");
                            startActivity(intent);
                        }
                    });
                    linearLayout3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "bank");
                            intent.putExtra("keyword", "bank");
                            startActivity(intent);
                        }
                    });
                    linearLayout4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "movie_theater");
                            intent.putExtra("keyword", "bioskop");
                            startActivity(intent);
                        }
                    });
                    linearLayout5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "cafe");
                            intent.putExtra("keyword", "kafe");
                            startActivity(intent);
                        }
                    });
                    linearLayout6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "local_government_office");
                            intent.putExtra("keyword", "gedung_pemerintah");
                            startActivity(intent);
                        }
                    });
                    linearLayout7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "school");
                            intent.putExtra("keyword", "kampus|universitas");
                            startActivity(intent);
                        }
                    });
                    linearLayout8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "police");
                            intent.putExtra("keyword", "kantor_polisi|pos_polisi");
                            startActivity(intent);
                        }
                    });
                    linearLayout9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "post_office");
                            intent.putExtra("keyword", "jne|pos_indonesia|jnt|tiki|wahana");
                            startActivity(intent);
                        }
                    });
                    linearLayout10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "shopping_mall");
                            intent.putExtra("keyword", "mall");
                            startActivity(intent);
                        }
                    });
                    linearLayout11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "mosque|hindu_temple|church");
                            intent.putExtra("keyword", "masjid");
                            startActivity(intent);
                        }
                    });
                    linearLayout12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "parking");
                            intent.putExtra("keyword", "tempat_parkir");
                            startActivity(intent);
                        }
                    });
                    linearLayout13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "library");
                            intent.putExtra("keyword", "perpustakaan");
                            startActivity(intent);
                        }
                    });
                    linearLayout14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "gas_station");
                            intent.putExtra("keyword", "spbu");
                            startActivity(intent);
                        }
                    });
                    linearLayout15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "hospital");
                            intent.putExtra("keyword", "rumah_sakit");
                            startActivity(intent);
                        }
                    });
                    linearLayout16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListActivity.class);
                            intent.putExtra("latitude", String.valueOf(latitude));
                            intent.putExtra("longitude", String.valueOf(longitude));
                            intent.putExtra("tag", "train_station");
                            intent.putExtra("keyword", "stasiun_kereta_api");
                            startActivity(intent);
                        }
                    });
                }else{
                    Toast.makeText(getActivity(),"No Location recorded",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Toast.makeText(getActivity(),"Location permission missing",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getResultListDest() {
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
                    hRecyclerView.setAdapter(mSlideAdapter);
                    mSlideAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(), "No result!" , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetDestinations> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
