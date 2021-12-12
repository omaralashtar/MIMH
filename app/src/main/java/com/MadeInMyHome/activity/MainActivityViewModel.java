package com.MadeInMyHome;


import android.content.Context;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;


import com.MadeInMyHome.Response.CategoryArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel {







    //___________________________________________________________________________________________________
    public MutableLiveData<String> show_Meal_category_horizental(String str, final Context c, final RecyclerView RecCat) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();


        Call<CategoryArrayListResponse> call = RestClient.getService().getCategoryProduct("");
        call.enqueue(new Callback<CategoryArrayListResponse>() {
            @Override
            public void onResponse(Call<CategoryArrayListResponse> call, Response<CategoryArrayListResponse> response) {

                CategoryArrayListResponse data = response.body();

                if (data != null) {

                    ArrayList<Category> arr = data.getArrayList();


                    if (arr.size() > 0) {

                        RecycleAdapterCategory a = new RecycleAdapterCategory(c, arr);
                        RecCat.setAdapter(a);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryArrayListResponse> call, Throwable t) {

            }
        });
        return myLiveDataList;
    }

    //_______________________________________________________________________________________________________







}



