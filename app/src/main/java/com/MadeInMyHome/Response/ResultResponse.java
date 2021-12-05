package com.MadeInMyHome.Response;

import com.google.gson.annotations.SerializedName;

public class ResultResponse
{
    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

}
