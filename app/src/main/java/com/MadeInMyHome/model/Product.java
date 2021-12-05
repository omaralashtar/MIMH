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

    @SerializedName("price")
    private float price;

    @SerializedName("size")
    private int size;

    @SerializedName("unit")
    private String unit;

    @SerializedName("count_visits")
    private int count_visits;

    @SerializedName("discount")
    private float discount;

    @SerializedName("discount_date")
    private String discount_date;

    @SerializedName("status")
    private boolean status;

    @SerializedName("deleted_at")
    private boolean deleted_at;

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

    public float getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public int getCount_visits() {
        return count_visits;
    }

    public float getDiscount() {
        return discount;
    }

    public String getDiscount_date() {
        return discount_date;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isDeleted_at() {
        return deleted_at;
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