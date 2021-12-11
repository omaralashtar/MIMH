package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.CategoryArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Category>> showCategoryProduct(final Context context) {
        return showCategoryProduct("", context);
    }

    public MutableLiveData<ArrayList<Category>> showCategoryProduct(String str, final Context context) {

        final MutableLiveData<ArrayList<Category>> arrayListMutableLiveData = new MutableLiveData<>();

        Call<CategoryArrayListResponse> call = RestClient.getService().getCategoryProduct(str);
        call.enqueue(new Callback<CategoryArrayListResponse>() {
            @Override
            public void onResponse(Call<CategoryArrayListResponse> call, Response<CategoryArrayListResponse> response) {
                CategoryArrayListResponse categoryArrayListResponse = response.body();
                if (categoryArrayListResponse != null) {
                    ArrayList<Category> categoryArrayList = categoryArrayListResponse.getArrayList();
                    if (categoryArrayList.size() > 0) {
                        arrayListMutableLiveData.setValue(categoryArrayList);
                    }
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }
}