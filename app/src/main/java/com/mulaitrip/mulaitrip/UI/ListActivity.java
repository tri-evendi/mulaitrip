package com.mulaitrip.mulaitrip.UI;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mulaitrip.mulaitrip.API.adapter.ListAdapter;
import com.mulaitrip.mulaitrip.API.model.GooglePlaces;
import com.mulaitrip.mulaitrip.API.services.ApiGoogle;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.PLACE_IMAGE;
import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.WEB_API_KEY;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNearbyPlaces;
    private ArrayList<GooglePlaces> gplaceList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManagerNearbyPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        String actionBarTitleText = getResources().getString(R.string.back);
        setTitle(actionBarTitleText);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManagerNearbyPlaces = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewNearbyPlaces = (RecyclerView) findViewById(R.id.recyclerViewNearbyPlaces);
        recyclerViewNearbyPlaces.setLayoutManager(layoutManagerNearbyPlaces);
        recyclerViewNearbyPlaces.setItemAnimator(new DefaultItemAnimator());


        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiGoogle.NEARBY_SEARCH + getIntent().getStringExtra("latitude") +","+
                getIntent().getStringExtra("longitude") + ApiGoogle.RANKBY + "&type="+ getIntent().getStringExtra("tag")+ "&keyword="+
                getIntent().getStringExtra("keyword")+ ApiGoogle.KEY, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Mulaitrip", "onResponse(Nearby Places): " + response);
                        try {
                            JSONObject parentObject = new JSONObject(response);
                            JSONArray parentArray = parentObject.getJSONArray("results");
                            for (int i = 0; i < parentArray.length(); i++) {
                                JSONObject finalObject = parentArray.getJSONObject(i);
                                GooglePlaces model = new GooglePlaces();
                                model.setTpName(finalObject.getString("name"));
                                model.setTpAddress(finalObject.getString("vicinity"));
                                model.setTpPlaceID(finalObject.getString("place_id"));
                                try {
                                    JSONArray photoArray = finalObject.getJSONArray("photos");
                                    JSONObject photoObject = photoArray.getJSONObject(0);
                                    model.setTpReference(PLACE_IMAGE + photoObject.getString("photo_reference") + "&key=" + WEB_API_KEY);
                                } catch (JSONException e) {
                                    Log.i("PhotoError", "onResponse: ");
                                }
                                gplaceList.add(model);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new ListAdapter(getApplicationContext(), gplaceList);
                        recyclerViewNearbyPlaces.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Some Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
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
