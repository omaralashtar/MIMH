package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Rate;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RateArrayListResponse {

    @SerializedName("result")
    private ArrayList<Rate> arrayList;

    public ArrayList<Rate> getArrayList() {
        return arrayList;
    }
}
