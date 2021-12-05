package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserArrayListResponse {

    @SerializedName("result")
    private ArrayList<User> arrayList;

    public ArrayList<User> getArrayList() {
        return arrayList;
    }
}
