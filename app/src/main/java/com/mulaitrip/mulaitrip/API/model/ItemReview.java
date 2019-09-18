package com.mulaitrip.mulaitrip.API.model;

/**
 * Created by Master on 25/07/2019.
 */

public class ItemReview {
    private String user;
    private String place;
    private String rate;
    private String review;


    public ItemReview( String user, String place, String rate, String review) {
        this.user = user;
        this.place = place;
        this.rate = rate;
        this.review = review;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
                "place='" + place + '\'' +
                "rate='" + rate + '\'' +
                ", review='" + review +
                '}';
    }
}
