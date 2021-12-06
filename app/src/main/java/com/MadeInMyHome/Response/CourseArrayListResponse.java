package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Course;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CourseArrayListResponse {

    @SerializedName("result")
    private ArrayList<Course> arrayList;

    public ArrayList<Course> getArrayList() {
        return arrayList;
    }
}
