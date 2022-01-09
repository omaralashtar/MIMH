package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rate implements Serializable {
    @SerializedName("id_user")
    private String id_user;

    @SerializedName("f_name")
    private String f_name;

    @SerializedName("l_name")
    private String l_name;

    @SerializedName("rating")
    private float rating;

    @SerializedName("comment")
    private String comment;

    public String getId_user() {
        return id_user;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }
}
