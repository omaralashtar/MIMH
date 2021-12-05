package com.MadeInMyHome.Response;

import android.media.Image;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ImageArrayListResponse {

    @SerializedName("result")
    private ArrayList<Image> arrayList;

    public ArrayList<Image> getArrayList() {
        return arrayList;
    }
}
