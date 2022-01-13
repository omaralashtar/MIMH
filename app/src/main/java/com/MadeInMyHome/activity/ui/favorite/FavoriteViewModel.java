package com.MadeInMyHome.activity.ui.favorite;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ProductArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Product>> getProducts(final Context context, String id, String next) {

        final MutableLiveData<ArrayList<Product>> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ProductArrayListResponse> call = RestClient.getService().getAllFavorite(id,next);
        call.enqueue(new Callback<ProductArrayListResponse>() {
            @Override
            public void onResponse(Call<ProductArrayListResponse> call, Response<ProductArrayListResponse> response) {
                ProductArrayListResponse productArrayListResponse = response.body();
                if (productArrayListResponse != null) {
                    ArrayList<Product> productArrayList = productArrayListResponse.getArrayList();
                    if (productArrayList.size() > 0) {
                        arrayListMutableLiveData.setValue(productArrayList);
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }
}