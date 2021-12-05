package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Courses;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CourseArrayListResponse {

    @SerializedName("result")
    private ArrayList<Courses> arrayList;

    public ArrayList<Courses> getArrayList() {
        return arrayList;
    }
}
