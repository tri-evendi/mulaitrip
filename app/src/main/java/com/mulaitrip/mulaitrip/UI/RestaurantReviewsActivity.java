package com.mulaitrip.mulaitrip.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mulaitrip.mulaitrip.API.model.ApiError;
import com.mulaitrip.mulaitrip.API.model.DetailPlaces;
import com.mulaitrip.mulaitrip.API.model.RestaurantReview;
import com.mulaitrip.mulaitrip.API.services.ApiClient;
import com.mulaitrip.mulaitrip.API.services.ApiInterface;
import com.mulaitrip.mulaitrip.MainActivity;
import com.mulaitrip.mulaitrip.R;
import com.mulaitrip.mulaitrip.UTILS.AppPrefences;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Master on 02/08/2019.
 */

public class RestaurantReviewsActivity extends AppCompatActivity {
    private static final String TAG = RestaurantReviewsActivity.class.getSimpleName();
    DetailPlaces dPlaces = new DetailPlaces();
    private Toolbar toolbar;
    private EditText setcomments;
    private TextView mRatingScale, titleComments;
    private RatingBar mRatingBar;
    ProgressBar progressBar;
    private static String token;
    private  static  String userid;
    private  static  String restaurantid;
//    private static String URL = "http://mulaitrip.com/api/reviews/places/create/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        restaurantid = String.valueOf((this.getIntent().getStringExtra("id")));
        token = AppPrefences.get(this).getString("token", "token");
        userid = AppPrefences.get(this).getString("id", "id");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setcomments = findViewById(R.id.comments);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnClear = findViewById(R.id.btnClear);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.textrating);
        titleComments = (TextView) findViewById(R.id.sukses);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setcomments.setError(null);
                setcomments.setText("");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rate = String.valueOf((int) mRatingBar.getRating()).trim();
                String review = setcomments.getText().toString().trim();
                if(!TextUtils.isEmpty(rate) && !TextUtils.isEmpty(review)) {
                    sendReview(userid, restaurantid, rate, review);
                }
            }
        });
    }

    private void sendReview(String userid, String restaurantid, String rate, String review) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        RestaurantReview restaurantReview= new RestaurantReview(userid, restaurantid, rate, review);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<RestaurantReview> call = apiInterface.restaurantReview(restaurantReview, token);
        call.enqueue(new Callback<RestaurantReview>() {
            @Override
            public void onResponse(Call<RestaurantReview> call, Response<RestaurantReview> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(RestaurantReviewsActivity.this, "Review submitted" + response.body(), Toast.LENGTH_LONG).show();
                    movetoMain(token);
                } else {
                    if (response.errorBody().contentType().subtype().equals("json")) {
                        ApiError apiError = ApiError.fromResponseBody(response.errorBody());
                        Toast.makeText(RestaurantReviewsActivity.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, apiError.getPath() + " " + apiError.getMessage());
                    } else {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<RestaurantReview> call, Throwable t) {
                Log.e(TAG, "Unable to submit reviews.");
            }

        });
    }

    private void movetoMain(String token) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
