package com.MadeInMyHome.activity.ui;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.WebService.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> checkToken(final Context context, String token) {

        final MutableLiveData<String> MutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().check(token);
        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse resultResponse = response.body();
                if (resultResponse != null) {
                    String result = resultResponse.getResult();

                    MutableLiveData.setValue(result);

                } else {
                    Toast.makeText(context, "Field Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return MutableLiveData;
    }

    public MutableLiveData<String> deleteToken(final Context context, String token) {

        final MutableLiveData<String> MutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().delete(token);
        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse resultResponse = response.body();
                if (resultResponse != null) {
                    String result = resultResponse.getResult();
                    if (result.equals("1")) {
                        MutableLiveData.setValue(result);
                    }
                } else {
                    Toast.makeText(context, "Field Logout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return MutableLiveData;
    }
}
