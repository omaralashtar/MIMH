package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

public class Courses {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("presenter")
    private String presenter;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("category")
    private String category;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPresenter() {
        return presenter;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }
}
