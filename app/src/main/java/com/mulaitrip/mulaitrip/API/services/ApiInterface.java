package com.mulaitrip.mulaitrip.API.services;

import com.mulaitrip.mulaitrip.API.model.GetDestinations;
import com.mulaitrip.mulaitrip.API.model.GetDetailPlaces;
import com.mulaitrip.mulaitrip.API.model.GetHotels;
import com.mulaitrip.mulaitrip.API.model.GetPlaces;
import com.mulaitrip.mulaitrip.API.model.GetRecs;
import com.mulaitrip.mulaitrip.API.model.GetRestaurants;
import com.mulaitrip.mulaitrip.API.model.HotelReview;
import com.mulaitrip.mulaitrip.API.model.ItemReview;
import com.mulaitrip.mulaitrip.API.model.Login;
import com.mulaitrip.mulaitrip.API.model.ProfileResponse;
import com.mulaitrip.mulaitrip.API.model.RestaurantReview;
import com.mulaitrip.mulaitrip.API.model.TokenResponse;
import com.mulaitrip.mulaitrip.API.model.User;
import com.mulaitrip.mulaitrip.API.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Master on 04-May-19.
 */

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("accounts/login/")
    Call<TokenResponse> login(@Body Login login);

    @Headers("Content-Type: application/json")
    @POST("accounts/register/")
    Call<UserResponse> register(@Body User user);

    @GET("accounts/profile/{id}/detail/")
    Call<ProfileResponse> getProfile(@Path("id") String id, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("reviews/places/create/")
    Call<ItemReview> placeReview(@Body ItemReview itemReview, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("reviews/hotels/create/")
    Call<HotelReview> hotelReview(@Body HotelReview hotelReview, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("reviews/restaurants/create/")
    Call<RestaurantReview> restaurantReview(@Body RestaurantReview restaurantReview, @Header("Authorization") String token);

    @GET("recommender/{id}")
    Call<GetRecs> getRecPlaces(@Path("id") String id, @Header("Authorization") String token);

    @GET("places/")
    Call<GetPlaces> getPlaces();

    @GET
    Call<GetDetailPlaces>getDetailPlaces(@Url String empty);

    @GET("destinations/")
    Call<GetDestinations> getDestinations();

    @GET("hotels/")
    Call<GetHotels> getHotels();

    @GET("restaurants/")
    Call<GetRestaurants> getRestaurants();
}
