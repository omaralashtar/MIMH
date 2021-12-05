package com.MadeInMyHome.Response;

import com.google.gson.annotations.SerializedName;

public class ResultImageResponse
{
    @SerializedName("result")
    private String result;

    @SerializedName("images")
    private String images;

    public String getResult() {
        return result;
    }

    public String getImages() {
        return images;
    }
}
