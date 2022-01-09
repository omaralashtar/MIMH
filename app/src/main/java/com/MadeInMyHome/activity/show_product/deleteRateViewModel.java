package com.MadeInMyHome.activity.show_product;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.RateArrayListResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Rate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteRateViewModel extends ViewModel {

    public MutableLiveData<String> DeleteRate(final Context context , String id_user ,String id_product ) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<ResultResponse> call = RestClient.getService().deleteRate(id_user,id_product);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("")) {
                          Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "done delete to Rate Product", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myLiveDataList;
    }





}
