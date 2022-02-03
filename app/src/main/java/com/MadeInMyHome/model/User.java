package com.MadeInMyHome.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("token")
    private String token;

    @SerializedName("email")
    private String email;

    @SerializedName("email_validate")
    private boolean email_validate;

    @SerializedName("f_name")
    private String f_name;

    @SerializedName("l_name")
    private String l_name;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("date")
    private String date;

    @SerializedName("gender")
    private String gender;

    @SerializedName("location")
    private String location;


    @SerializedName("phone")
    private String phone;

    @SerializedName("code")
    private String code;

    @SerializedName("deleted_at")
    private String deleted_at;

    @SerializedName("create_date")
    private String create_date;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean getEmail_validate() {
        return email_validate;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    //call api here for update image
    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }
}
