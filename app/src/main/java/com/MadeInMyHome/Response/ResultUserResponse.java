package com.MadeInMyHome.Response;

import com.google.gson.annotations.SerializedName;

public class ResultUserResponse
{
    @SerializedName("result")
    private String result;

    @SerializedName("id")
    private String id;

    public String getResult() {
        return result;
    }

    public String getId() {
        return id;
    }
}
