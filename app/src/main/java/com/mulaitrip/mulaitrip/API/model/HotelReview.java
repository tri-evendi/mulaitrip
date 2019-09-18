package com.mulaitrip.mulaitrip.API.model;

/**
 * Created by Master on 02/08/2019.
 */

public class HotelReview {
    private String user;
    private String hotel;
    private String rate;
    private String review;

    public HotelReview(String user, String hotel, String rate, String review) {
        this.user = user;
        this.hotel = hotel;
        this.rate = rate;
        this.review = review;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
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
                "hotel='" + hotel + '\'' +
                "rate='" + rate + '\'' +
                ", review='" + review +
                '}';
    }
}
