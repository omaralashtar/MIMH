package com.MadeInMyHome.activity.ui.courses;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.CourseArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Course;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Course>> getCourses(final Context context, String next){
        return getCourses(context,"",next);
    }
    public MutableLiveData<ArrayList<Course>> getCourses(final Context context, String filter, String next) {

        final MutableLiveData<ArrayList<Course>> arrayListMutableLiveData = new MutableLiveData<>();

        Call<CourseArrayListResponse> call = RestClient.getService().getAllCourse(filter,next);
        call.enqueue(new Callback<CourseArrayListResponse>() {
            @Override
            public void onResponse(Call<CourseArrayListResponse> call, Response<CourseArrayListResponse> response) {
                CourseArrayListResponse courseArrayListResponse = response.body();
                if (courseArrayListResponse != null) {
                    ArrayList<Course> courseArrayList = courseArrayListResponse.getArrayList();
                    if (courseArrayList.size() > 0) {
                        arrayListMutableLiveData.setValue(courseArrayList);
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CourseArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

}