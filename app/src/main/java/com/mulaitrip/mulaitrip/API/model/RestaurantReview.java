package com.mulaitrip.mulaitrip.API.model;

/**
 * Created by Master on 02/08/2019.
 */

public class RestaurantReview {
    private String user;
    private String restaurant;
    private String rate;
    private String review;

    public RestaurantReview(String user, String restaurant, String rate, String review) {
        this.user = user;
        this.restaurant = restaurant;
        this.rate = rate;
        this.review = review;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Post{" +
                "user='" + user + '\'' +
                "restaurant='" + restaurant + '\'' +
                "rate='" + rate + '\'' +
                ", review='" + review +
                '}';
    }
}
