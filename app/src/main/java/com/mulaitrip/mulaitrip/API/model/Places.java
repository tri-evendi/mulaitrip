package com.mulaitrip.mulaitrip.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Master on 03-May-19.
 */

public class Places {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("detail_url")
    @Expose
    private String detail_url;
    @SerializedName("category_url")
    @Expose
    private String category_url;

    @SerializedName("destinations")
    @Expose
    private String destinations;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("googleID")
    @Expose
    private String googleID;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("number_of_views")
    @Expose
    private Integer numberofviews;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("website")
    @Expose
    private String website;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("avgrating")
    @Expose
    private String avgrating;

    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("block_review")
    @Expose
    private String block_review;


    public Places() {
        this.id = id;
        this.detail_url = detail_url;
        this.category_url = category_url;
        this.destinations = destinations;
        this.slug = slug;
        this.name = name;
        this.googleID = googleID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.category = category;
        this.image = image;
        this.price = price;
        this.numberofviews = numberofviews;
        this.description = description;
        this.phone = phone;
        this.website = website;
        this.status = status;
        this.avgrating = avgrating;
        this.publish = publish;
        this.block_review = block_review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getCategory_url() {
        return category_url;
    }

    public void setCategory_url(String category_url) {
        this.category_url = category_url;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumberofviews() {
        return numberofviews;
    }

    public void setNumberofviews(Integer numberofviews) {
        this.numberofviews = numberofviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(String avgrating) {
        this.avgrating = avgrating;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getBlock_review() {
        return block_review;
    }

    public void setBlock_review(String block_review) {
        this.block_review = block_review;
    }
}