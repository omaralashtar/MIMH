package com.MadeInMyHome.activity.show_course;

import android.content.Context;
import android.widget.Toast;

import com.MadeInMyHome.Response.CourseArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Course;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {

    public MutableLiveData<Course> getCourse(final Context context, String id) {

        final MutableLiveData<Course> MutableLiveData = new MutableLiveData<>();

        Call<CourseArrayListResponse> call = RestClient.getService().getCourse(id);
        call.enqueue(new Callback<CourseArrayListResponse>() {
            @Override
            public void onResponse(Call<CourseArrayListResponse> call, Response<CourseArrayListResponse> response) {
                CourseArrayListResponse courseArrayListResponse = response.body();
                if (courseArrayListResponse != null) {
                    ArrayList<Course> courseArrayList = courseArrayListResponse.getArrayList();
                    if (courseArrayList.size() > 0) {
                        MutableLiveData.setValue(courseArrayList.get(0));
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

        return MutableLiveData;
    }

}
