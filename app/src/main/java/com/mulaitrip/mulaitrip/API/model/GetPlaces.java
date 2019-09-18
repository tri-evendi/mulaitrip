package com.mulaitrip.mulaitrip.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Master on 07-May-19.
 */

public class GetPlaces {
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("results")
    @Expose
    private List<Places> results;
    @SerializedName("previous")
    @Expose
    private String previous;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Places> getResults() {
        return results;
    }

    public void setResults(List<Places> results) {
        this.results = results;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
