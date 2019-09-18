package com.mulaitrip.mulaitrip.UI;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mulaitrip.mulaitrip.API.adapter.PlaceListAdapter;
import com.mulaitrip.mulaitrip.API.model.SearchPlaces;
import com.mulaitrip.mulaitrip.API.services.ApiGoogle;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceSearchResultActivity extends AppCompatActivity {

    public static final String TAG = PlaceSearchResultActivity.class.getSimpleName();
    /**
     * ArrayList to store nearbyPlace details
     */
    private ArrayList<SearchPlaces> mNearByPlaceArrayList = new ArrayList<>();
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search_result);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        String actionBarTitleText = getString(R.string.search_result_string);
        setTitle(actionBarTitleText);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String locationName = intent.getStringExtra(SearchManager.QUERY);

            String currentLocation = getSharedPreferences(
                    ApiGoogle.CURRENT_LOCATION_SHARED_PREFERENCE_KEY, 0)
                    .getString(ApiGoogle.CURRENT_LOCATION_DATA_KEY, null);

//            String locationQueryStringUrl = ApiGoogle.BASE_URL + ApiGoogle.SEARCH_TAG + "/" +
//                    ApiGoogle.JSON_FORMAT_TAG + "?" + ApiGoogle.QUERY_TAG + "=" + "&" + ApiGoogle.LOCATION_TAG + "=" +
//                    currentLocation + "&" + ApiGoogle.RANK_BY_TAG + "=" + ApiGoogle.DISTANCE_TAG +
//                    "&" + ApiGoogle.KEYWORD_TAG + "=" + locationName.replace(" ", "%20") + "&" +
//                    ApiGoogle.API_KEY_TAG + "=" + ApiGoogle.WEB_API_KEY;

            String locationQueryStringUrl = ApiGoogle.BASE_URL_SEARCH + ApiGoogle.QUERY_TAG + "=" + locationName + "&" + ApiGoogle.LOCATION_TAG + "=" +
                    currentLocation + "&" + ApiGoogle.RADIUS_TAG + "=" + ApiGoogle.RADIUS_VALUE +
                    "&" + ApiGoogle.API_KEY_TAG + "=" + ApiGoogle.WEB_API_KEY;
            Log.d(TAG, locationQueryStringUrl);

            getLocationDetails(locationQueryStringUrl);

        }
    }

    private void getLocationDetails(String locationQueryStringUrl) {
        //Tag to cancel request
        String jsonArrayTag = "jsonArrayTag";
        JsonObjectRequest placeJsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                locationQueryStringUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mProgressBar.setVisibility(View.GONE);
                            JSONArray rootJsonArray = response.getJSONArray("results");
                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject singlePlaceJsonObject = (JSONObject) rootJsonArray.get(i);
                                String currentPlaceId = singlePlaceJsonObject.getString("place_id");
                                Double currentPlaceLatitude = singlePlaceJsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                                Double currentPlaceLongitude = singlePlaceJsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                                String currentPlaceName = singlePlaceJsonObject.getString("name");
//                                String currentPlaceOpeningHourStatus = singlePlaceJsonObject.has("opening_hours") ?
//                                        singlePlaceJsonObject.getJSONObject("opening_hours").getString("open_now") : "Status Not Available";
                                Double currentPlaceRating = singlePlaceJsonObject.has("rating") ? singlePlaceJsonObject.getDouble("rating") : 0;
                                String currentPlaceAddress = singlePlaceJsonObject.has("formatted_address") ? singlePlaceJsonObject.getString("formatted_address") : "Address Not Available";
                                SearchPlaces singlePlaceDetail = new SearchPlaces(
                                        currentPlaceId,
                                        currentPlaceLatitude,
                                        currentPlaceLongitude,
                                        currentPlaceName,
                                        currentPlaceRating,
                                        currentPlaceAddress);
                                mNearByPlaceArrayList.add(singlePlaceDetail);
                                Log.d("Mulaitrip Get", "Jumlah data Search Resul: " + String.valueOf(mNearByPlaceArrayList.size()));
                            }
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewResultPlaces);

                            if (mNearByPlaceArrayList.size() == 0) {
                                findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            } else {
                                findViewById(R.id.empty_view).setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                PlaceListAdapter placeListAdapter = new PlaceListAdapter(PlaceSearchResultActivity.this, mNearByPlaceArrayList);
                                recyclerView.setLayoutManager( new LinearLayoutManager(PlaceSearchResultActivity.this));
                                recyclerView.setAdapter(placeListAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {

                            JSONObject singlePlaceJsonObject = new JSONObject(error.toString());

                        }catch (JSONException e){

                            e.printStackTrace();
                        }
                    }
                });
        //Adding request to request queue
        AppController.getInstance().addToRequestQueue(placeJsonObjectRequest, jsonArrayTag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
