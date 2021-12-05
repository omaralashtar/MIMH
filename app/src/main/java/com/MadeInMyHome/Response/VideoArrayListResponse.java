package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Video;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoArrayListResponse {

    @SerializedName("result")
    private ArrayList<Video> arrayList;

    public ArrayList<Video> getArrayList() {
        return arrayList;
    }
}
