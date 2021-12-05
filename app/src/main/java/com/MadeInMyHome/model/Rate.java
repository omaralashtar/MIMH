package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rate implements Serializable
{
    @SerializedName("id_user")
    private String id_user;

    @SerializedName("rating")
    private String rating;

    @SerializedName("comment")
    private String comment;

    public String getId_user() {
        return id_user;
    }

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
