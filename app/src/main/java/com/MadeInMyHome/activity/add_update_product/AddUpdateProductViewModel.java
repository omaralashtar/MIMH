package com.MadeInMyHome.activity.add_update_product;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.CategoryArrayListResponse;
import com.MadeInMyHome.Response.ResultImageResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUpdateProductViewModel extends ViewModel {

    public MutableLiveData<String> addProduct(final Context context, String name, String description,
                                              String image, float price, int size, String unit, float discount, String discount_date,
                                              String product_date, int category, String id_user, ArrayList<String> images) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultImageResponse> call = RestClient.getService().addProduct(name, description, image, price, size, unit, discount, discount_date,
                 product_date, category, id_user, images);
        call.enqueue(new Callback<ResultImageResponse>() {
            @Override
            public void onResponse(Call<ResultImageResponse> call, Response<ResultImageResponse> response) {
                ResultImageResponse resultImageResponse = response.body();
                if (resultImageResponse != null) {
                    String result = resultImageResponse.getResult();
                    String image = resultImageResponse.getImages();
                    if (result.equals("1")) {
                        arrayListMutableLiveData.setValue(result);
                    }
                    if (image.equals("0")) {
                        Toast.makeText(context, "upload image failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "failed get items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultImageResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> updateProduct(final Context context, String id, String name, String description,
                                                 String image, float price, int size, String unit, float discount, String discount_date, boolean status, int category) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().updateProduct(id, name, description, price, size, unit, discount, discount_date,
                status, category);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> updateProductImage(final Context context, String id, String image) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().updateProductImage(id, image);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> updateProductMultiImage(final Context context, String id, String image) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().updateProductMultiImages(id, image);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> getCategoryId(final Context context, String name) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<CategoryArrayListResponse> call = RestClient.getService().getCategoryIdByName(name);
        call.enqueue(new Callback<CategoryArrayListResponse>() {
            @Override
            public void onResponse(Call<CategoryArrayListResponse> call, Response<CategoryArrayListResponse> response) {
                CategoryArrayListResponse categoryArrayListResponse = response.body();
                if (categoryArrayListResponse != null) {
                    ArrayList<Category> categoryArrayList = categoryArrayListResponse.getArrayList();
                    if (categoryArrayList.size() > 0) {
                        arrayListMutableLiveData.setValue(String.valueOf(categoryArrayList.get(0).getId()));
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
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
