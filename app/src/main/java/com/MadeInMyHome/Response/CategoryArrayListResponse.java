package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryArrayListResponse {

    @SerializedName("result")
    private ArrayList<Category> arrayList;

    public ArrayList<Category> getArrayList() {
        return arrayList;
    }
}
