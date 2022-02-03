package com.MadeInMyHome.activity.search;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ProductArrayListResponse;
import com.MadeInMyHome.Response.UserArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {

    public MutableLiveData<ArrayList<User>> getAllUser(final Context context, String code) {

        final MutableLiveData<ArrayList<User>> MutableLiveData = new MutableLiveData<>();

        Call<UserArrayListResponse> call = RestClient.getService().getAllUser(code);
        call.enqueue(new Callback<UserArrayListResponse>() {
            @Override
            public void onResponse(Call<UserArrayListResponse> call, Response<UserArrayListResponse> response) {
                UserArrayListResponse UserArrayListResponse = response.body();
                if (UserArrayListResponse != null) {
                    ArrayList<User> UserArrayList = UserArrayListResponse.getArrayList();
                    if (UserArrayList.size() > 0) {
                        MutableLiveData.setValue(UserArrayList);
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return MutableLiveData;
    }

}
