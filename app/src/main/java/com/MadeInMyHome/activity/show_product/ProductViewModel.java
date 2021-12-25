package com.MadeInMyHome.activity.show_product;

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

public class ProductViewModel extends ViewModel {

    public MutableLiveData<Product> getProduct(final Context context, String id) {

        final MutableLiveData<Product> MutableLiveData = new MutableLiveData<>();

        Call<ProductArrayListResponse> call = RestClient.getService().getProduct(id);
        call.enqueue(new Callback<ProductArrayListResponse>() {
            @Override
            public void onResponse(Call<ProductArrayListResponse> call, Response<ProductArrayListResponse> response) {
                ProductArrayListResponse productArrayListResponse = response.body();
                if (productArrayListResponse != null) {
                    ArrayList<Product> ProductArrayList = productArrayListResponse.getArrayList();
                    if (ProductArrayList.size() > 0) {
                        MutableLiveData.setValue(ProductArrayList.get(0));
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

        return MutableLiveData;
    }

}
