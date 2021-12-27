package com.MadeInMyHome.Response;

import android.media.Image;

import com.MadeInMyHome.model.Images;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ImageArrayListResponse {

    @SerializedName("result")
    private ArrayList<Images> arrayList;

    public ArrayList<Images> getArrayList() {
        return arrayList;
    }
}
