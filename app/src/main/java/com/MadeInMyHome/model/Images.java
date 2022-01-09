package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Images implements Serializable
{
    @SerializedName("id")
    private String id;

    @SerializedName("id_product")
    private String id_product;

    @SerializedName("image")
    private String image;

    public Images() {
        id=null;
        id_product=null;
        image=null;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getId_product() {
        return id_product;
    }

    public String getImage() {
        return image;
    }
}
