package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable
{
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("video_order ")
    private String video_order ;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getVideo_order() {
        return video_order;
    }
}
