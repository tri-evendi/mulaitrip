package com.mulaitrip.mulaitrip.API.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Master on 23/07/2019.
 */

public class TokenResponse implements Serializable {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("id")
    @Expose
    private String id;

    private final static long serialVersionUID = 8022889269386527992L;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
