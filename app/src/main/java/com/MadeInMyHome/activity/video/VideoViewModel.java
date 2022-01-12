package com.MadeInMyHome.activity.video;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.CategoryArrayListResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.Response.VideoArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Video;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Video>> getVideos(final Context context,String id) {

        final MutableLiveData<ArrayList<Video>> arrayListMutableLiveData = new MutableLiveData<>();

        Call<VideoArrayListResponse> call = RestClient.getService().getVideo(id);
        call.enqueue(new Callback<VideoArrayListResponse>() {
            @Override
            public void onResponse(Call<VideoArrayListResponse> call, Response<VideoArrayListResponse> response) {
                VideoArrayListResponse videoArrayListResponse = response.body();
                if (videoArrayListResponse != null) {
                    ArrayList<Video> videoArrayList = videoArrayListResponse.getArrayList();
                    if (videoArrayList.size() > 0) {
                        arrayListMutableLiveData.setValue(videoArrayList);
                    }
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VideoArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }
    public MutableLiveData<String> reportVideos(final Context context,String id_user,String id_video,String message) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().addReport(id_user,id_video,message);
        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse resultResponse = response.body();
                if (resultResponse != null) {
                   String result = resultResponse.getResult();
                    if (result.equals("1")) {
                        arrayListMutableLiveData.setValue(result);
                    }
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

}