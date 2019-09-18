package com.mulaitrip.mulaitrip.API.services;

/**
 * Created by Master on 17-May-19.
 */

public class ApiGoogle {
    public static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    public static final String NEARBY_SEARCH_TAG = "nearbysearch";
    public static final String SEARCH_TAG = "textsearch";
    public static final String QUERY_TAG = "query";
    public static final String JSON_FORMAT_TAG = "json";
    public static final String LOCATION_TAG = "location";
    public static final String RADIUS_TAG = "radius";
    public static final String RADIUS_VALUE = "10000";
    public static final String PLACE_TYPE_TAG = "type";
    public static final String NEXT_PAGE_TOKEN_TAG = "pagetoken";
    public static final String API_KEY_TAG = "key";
    public static final String RANK_BY_TAG = "rankby";
    public static final String DISTANCE_TAG = "distance";
    public static final String KEYWORD_TAG = "keyword";
    public static final String LOCATION_DETAIL_TAG = "details";
    public static final String LOCATION_PLACE_ID_TAG = "placeid";
    public static final String LOCATION_TYPE_EXTRA_TEXT = "location_type";
    public static final String LOCATION_NAME_EXTRA_TEXT = "location_name";
    public static final String ALL_NEARBY_LOCATION_KEY = "all_nearby_location_key";
    public static final String LOCATION_ID_EXTRA_TEXT = "location_id";
    public static final String CURRENT_LOCATION_DATA_KEY = "current_location_data";
    public static final String CURRENT_LOCATION_SHARED_PREFERENCE_KEY = "shared_preference_key";
    public static final String CURRENT_LOCATION_USER_RATING_KEY = "current_location_user_rating";

    public final static String API_KEY = "AIzaSyD67eUvLlJWq_CMS2KcCAR8vwMY_xuczK8";
    public final static String WEB_API_KEY = "AIzaSyAPvPv8IPRaS2wzwfnjWR5slDTCpHclsRc";

    //Places Images
    public final static String PLACE_IMAGE = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";

    //Nearby Places
    public final static String NEARBY_PLACES_1 = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public final static String POINT_OF_INTEREST = "+point+of+interest&language=en&key=" + WEB_API_KEY;


    public final static String NEARBY_SEARCH="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
    public final static String RANKBY= "&rankby=distance";
    public final static String RADIUS="&radius=1000";
    public final static String KEY="&key=" + WEB_API_KEY;



    public final static String RESTAURANTS_1 = "https://maps.googleapis.com/maps/api/place/textsearch/json?location=";
    public final static String RESTAURANTS_2= "&radius=500&type=restaurant&key=" + WEB_API_KEY;

    public final static String HOTELS_2= "&radius=500&type=lodging&key=" + WEB_API_KEY;


    public final static String DETAILS= "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    public final static String DETAILS_2= "&rankby=distance&key=" + WEB_API_KEY;


    public static final String BASE_URL_SEARCH = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
    public final static String POINT_OF_INTEREST_NEAR = "+monuments+in&radius=10000&rankby=prominence&language=en&key=" + WEB_API_KEY;

}
