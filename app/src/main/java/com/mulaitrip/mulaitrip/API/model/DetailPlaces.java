package com.mulaitrip.mulaitrip.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Master on 26/07/2019.
 */

public class DetailPlaces {
    @SerializedName("all_places_url")
    @Expose
    private String all_places_url;
    @SerializedName("add_review_url")
    @Expose
    private String add_review_url;
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("destinations")
    @Expose
    private String destinations;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
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
    private String price;

    @SerializedName("number_of_views")
    @Expose
    private String number_of_views;
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
    @SerializedName("added")
    @Expose
    private String added;
    @SerializedName("updated")
    @Expose
    private String updated;

    public DetailPlaces() {
        this.all_places_url = all_places_url;
        this.add_review_url = add_review_url;
        this.id = id;
        this.destinations = destinations;
        this.name = name;
        this.slug = slug;
        this.googleID = googleID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.category = category;
        this.image = image;
        this.price = price;
        this.number_of_views = number_of_views;
        this.description = description;
        this.phone = phone;
        this.website = website;
        this.status = status;
        this.avgrating = avgrating;
        this.publish = publish;
        this.block_review = block_review;
        this.added = added;
        this.updated = updated;
    }

    public String getAll_places_url() {
        return all_places_url;
    }

    public void setAll_places_url(String all_places_url) {
        this.all_places_url = all_places_url;
    }

    public String getAdd_review_url() {
        return add_review_url;
    }

    public void setAdd_review_url(String add_review_url) {
        this.add_review_url = add_review_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber_of_views() {
        return number_of_views;
    }

    public void setNumber_of_views(String number_of_views) {
        this.number_of_views = number_of_views;
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

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
