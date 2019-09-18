package com.mulaitrip.mulaitrip.API.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Master on 22/07/2019.
 */

public class SearchPlaces implements Parcelable {
    public static final Parcelable.Creator<SearchPlaces> CREATOR = new Parcelable.Creator<SearchPlaces>() {

        @Override
        public SearchPlaces createFromParcel(Parcel source) {
            return new SearchPlaces(source);
        }

        @Override
        public SearchPlaces[] newArray(int size) {
            return new SearchPlaces[size];
        }
    };
    /**
     * All Reference Variable
     */
    private String mPlaceId;
    private Double mPlaceLatitude;
    private Double mPlaceLongitude;
    private String mPlaceName;
    private String mPlaceOpeningHourStatus;
    private Double mPlaceRating;
    private String mPlaceAddress;
    private String mPlacePhoneNumber;
    private String mPlaceWebsite;
    private String mPlaceShareLink;


    public SearchPlaces(String mPlaceId, Double mPlaceLatitude, Double mPlaceLongitude,
                 String mPlaceName ,
                 Double mPlaceRating, String mPlaceAddress) {

        this.mPlaceId = mPlaceId;
        this.mPlaceLatitude = mPlaceLatitude;
        this.mPlaceLongitude = mPlaceLongitude;
        this.mPlaceName = mPlaceName;
        this.mPlaceRating = mPlaceRating;
        this.mPlaceAddress = mPlaceAddress;
    }

    public SearchPlaces(String mPlaceId, Double mPlaceLatitude, Double mPlaceLongitude,
                 String mPlaceName, String mPlaceOpeningHourStatus,
                 Double mPlaceRating, String mPlaceAddress,
                 String mPlacePhoneNumber, String mPlaceWebsite,
                 String mPlaceShareLink) {

        this.mPlaceId = mPlaceId;
        this.mPlaceLatitude = mPlaceLatitude;
        this.mPlaceLongitude = mPlaceLongitude;
        this.mPlaceName = mPlaceName;
        this.mPlaceRating = mPlaceRating;
        this.mPlaceAddress = mPlaceAddress;
        this.mPlaceOpeningHourStatus = mPlaceOpeningHourStatus;
        this.mPlacePhoneNumber = mPlacePhoneNumber;
        this.mPlaceWebsite = mPlaceWebsite;
        this.mPlaceShareLink = mPlaceShareLink;
    }

    public SearchPlaces(String mPlaceId, Double mPlaceLatitude, Double mPlaceLongitude) {
        this.mPlaceId = mPlaceId;
        this.mPlaceLatitude = mPlaceLatitude;
        this.mPlaceLongitude = mPlaceLongitude;
    }

    /**
     * Retrieving Place data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/

    private SearchPlaces(Parcel in) {
        this.mPlaceId = in.readString();
        this.mPlaceLatitude = in.readDouble();
        this.mPlaceLongitude = in.readDouble();
        this.mPlaceName = in.readString();
        this.mPlaceOpeningHourStatus = in.readString();
        this.mPlaceRating = in.readDouble();
        this.mPlaceAddress = in.readString();
    }

    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String placeId) {
        mPlaceId = placeId;
    }

    public Double getPlaceLatitude() {
        return mPlaceLatitude;
    }

    public void setPlaceLatitude(Double placeLatitude) {
        mPlaceLatitude = placeLatitude;
    }

    public Double getPlaceLongitude() {
        return mPlaceLongitude;
    }

    public void setPlaceLongitude(Double placeLongitude) {
        mPlaceLongitude = placeLongitude;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public void setPlaceName(String placeName) {
        mPlaceName = placeName;
    }

    public String getPlaceOpeningHourStatus() {
        return mPlaceOpeningHourStatus;
    }

    public void setPlaceOpeningHourStatus(String placeOpeningHourStatus) {
        mPlaceOpeningHourStatus = placeOpeningHourStatus;
    }

    public Double getPlaceRating() {
        return mPlaceRating;
    }

    public void setPlaceRating(Double placeRating) {
        mPlaceRating = placeRating;
    }

    public String getPlaceAddress() {
        return mPlaceAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        mPlaceAddress = placeAddress;
    }

    public String getPlacePhoneNumber() {
        return mPlacePhoneNumber;
    }

    public void setPlacePhoneNumber(String placePhoneNumber) {
        mPlacePhoneNumber = placePhoneNumber;
    }

    public String getPlaceWebsite() {
        return mPlaceWebsite;
    }

    public void setPlaceWebsite(String placeWebsite) {
        mPlaceWebsite = placeWebsite;
    }

    public String getPlaceShareLink() {
        return mPlaceShareLink;
    }

    public void setPlaceShareLink(String placeShareLink) {
        mPlaceShareLink = placeShareLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Storing the Place data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPlaceId);
        dest.writeDouble(mPlaceLatitude);
        dest.writeDouble(mPlaceLongitude);
        dest.writeString(mPlaceName);
        dest.writeString(mPlaceOpeningHourStatus);
        dest.writeDouble(mPlaceRating);
        dest.writeString(mPlaceAddress);
    }
}
