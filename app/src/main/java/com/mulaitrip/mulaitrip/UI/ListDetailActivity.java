package com.mulaitrip.mulaitrip.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.DETAILS;
import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.DETAILS_2;
import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.PLACE_IMAGE;
import static com.mulaitrip.mulaitrip.API.services.ApiGoogle.WEB_API_KEY;

public class ListDetailActivity extends AppCompatActivity {

    private ImageView imageViewBackpic, imageViewCall, imageViewNavigation;
    private TextView textViewName, textViewAdd, internationalphone;

    private double lat, lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        String actionBarTitleText = getResources().getString(R.string.back);
        setTitle(actionBarTitleText);
        actionBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageViewBackpic = (ImageView) findViewById(R.id.imageViewBackpic);
        imageViewCall = (ImageView) findViewById(R.id.imageViewCall);
        imageViewNavigation = (ImageView) findViewById(R.id.imageViewNavigation);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAdd = (TextView) findViewById(R.id.textViewAdd);
        internationalphone = (TextView) findViewById(R.id.internationalphone);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, DETAILS + getIntent().getStringExtra("place_id") + DETAILS_2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Volley", "onResponse(Details): " + response);
                        try {
                            JSONObject parentObject = new JSONObject(response);
                            final JSONObject childObject = parentObject.getJSONObject("result");
                            textViewName.setText(childObject.getString("name"));
                            textViewAdd.setText(childObject.getString("formatted_address"));
                            internationalphone.setText(childObject.getString("international_phone_number"));
                            imageViewCall.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = null;
                                    try {
                                        intent = new Intent(Intent.ACTION_DIAL);
                                        intent.setData(Uri.parse("tel:" + childObject.getString("international_phone_number")));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(intent);
                                }
                            });

                            imageViewNavigation.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        JSONObject jobj = childObject.getJSONObject("geometry");
                                        JSONObject jobj2 = jobj.getJSONObject("location");
                                        lat = Double.parseDouble(jobj2.getString("lat"));
                                        lng = Double.parseDouble(jobj2.getString("lng"));
                                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                Uri.parse("google.navigation:q=" + jobj2.getString("lat") + ","+ jobj2.getString("lng")));
                                        intent.setPackage("com.google.android.apps.maps");
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                            try {
                                JSONArray photoArray = childObject.getJSONArray("photos");
                                JSONObject photoObject = photoArray.getJSONObject(0);
                                Glide.with(getApplicationContext()).load(PLACE_IMAGE + photoObject.getString("photo_reference") + "&key=" + WEB_API_KEY).into(imageViewBackpic);
                            } catch (JSONException e) {
                                Log.i("PhotoError", "onResponse: ");
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
