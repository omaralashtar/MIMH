package com.MadeInMyHome.activity.add_update_product;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ResultImageResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.WebService.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUpdateProductViewModel extends ViewModel {

    public MutableLiveData<String> addProduct(final Context context, String name, String description,
                                              String image, String price, String size, String unit, String discount, String discount_date,
                                              String product_date, String category, String id_user,
                                              String image1, String image2, String image3, Button add) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultImageResponse> call = RestClient.getService().addProduct(name, description, image, price, size, unit, discount, discount_date,
                product_date, category, id_user, image1, image2, image3);
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
                        add.setClickable(true);
                    }
                } else {
                    Toast.makeText(context, "failed get items", Toast.LENGTH_SHORT).show();
                    add.setClickable(true);
                }
            }

            @Override
            public void onFailure(Call<ResultImageResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                add.setClickable(true);
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> addMultiImage(final Context context, String id, String image) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultUserResponse> call = RestClient.getService().addMultiImage(id, image);
        call.enqueue(new Callback<ResultUserResponse>() {
            @Override
            public void onResponse(Call<ResultUserResponse> call, Response<ResultUserResponse> response) {
                ResultUserResponse resultResponse = response.body();
                if (resultResponse != null) {
                    String result = resultResponse.getResult();
                    if (result.equals("1")) {
                        String id = resultResponse.getId();
                        arrayListMutableLiveData.setValue(id);
                    }
                } else {
                    Toast.makeText(context, "failed get items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultUserResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return arrayListMutableLiveData;
    }

    public MutableLiveData<String> updateProduct(final Context context, String id, String name, String description,
                                                 String price, String size, String unit, String discount,
                                                 String discount_date, String category, Button update) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().updateProduct(id, name, description, price, size, unit,
                discount, discount_date, category);
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
                    update.setClickable(true);
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                update.setClickable(true);
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

    public MutableLiveData<String> deleteProduct(final Context context, String id_product) {

        final MutableLiveData<String> arrayListMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().deleteProduct(id_product);
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

}
