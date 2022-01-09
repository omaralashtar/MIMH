package com.MadeInMyHome.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private String price;

    @SerializedName("size")
    private String size;

    @SerializedName("unit")
    private String unit;

    @SerializedName("discount")
    private String discount;

    @SerializedName("discount_date")
    private String discount_date;

    @SerializedName("status")
    private boolean status;

    @SerializedName("product_date")
    private String product_date;

    @SerializedName("category")
    private String category;

    @SerializedName("id_user")
    private String id_user ;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getDiscount() {
        return discount;
    }

    public String getDiscount_date() {
        return discount_date;
    }

    public boolean isStatus() {
        return status;
    }

    public String getProduct_date() {
        return product_date;
    }

    public String getCategory() {
        return category;
    }

    public String getId_user() {
        return id_user;
    }
}