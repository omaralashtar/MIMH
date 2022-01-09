package com.MadeInMyHome.activity.show_product;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.RateArrayListResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.Rate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends ViewModel {


    public MutableLiveData<String> addComment(final Context context, String id_user,String id_product,String rate,String comment) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().addRate(id_user, id_product,rate,comment);
        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                        String res = data.getResult ();
                        arrayListMutableLiveData.setValue(res);

                    } else {
                        Toast.makeText(context, "Field_Add Comment", Toast.LENGTH_SHORT).show();
                    }

                }}

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }





    public MutableLiveData<ArrayList<Rate>> getRateAll(final Context context, String id, String next) {

        final MutableLiveData<ArrayList<Rate>> MutableLiveData = new MutableLiveData<>();

        Call<RateArrayListResponse> call = RestClient.getService().getAllRate(id,next);
        call.enqueue(new Callback<RateArrayListResponse>() {
            @Override
            public void onResponse(Call<RateArrayListResponse> call, Response<RateArrayListResponse> response) {
                RateArrayListResponse rateArrayListResponse = response.body();
                if (rateArrayListResponse != null) {
                    ArrayList<Rate> rateArrayList = rateArrayListResponse.getArrayList();
                    if (rateArrayList.size() > 0) {
                        MutableLiveData.setValue(rateArrayList);
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RateArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return MutableLiveData;
    }


}
