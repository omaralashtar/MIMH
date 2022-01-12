package com.MadeInMyHome.activity.show_product;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.R;
import com.MadeInMyHome.Response.ImageArrayListResponse;
import com.MadeInMyHome.Response.ProductArrayListResponse;
import com.MadeInMyHome.Response.RateArrayListResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.Rate;

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
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return MutableLiveData;
    }

    public MutableLiveData<ArrayList<Images>> getProductMultiImage(final Context context, String id) {

        final MutableLiveData<ArrayList<Images>> MutableLiveData = new MutableLiveData<>();

        Call<ImageArrayListResponse> call = RestClient.getService().getMultiImagesProduct(id);
        call.enqueue(new Callback<ImageArrayListResponse>() {
            @Override
            public void onResponse(Call<ImageArrayListResponse> call, Response<ImageArrayListResponse> response) {
                ImageArrayListResponse imageArrayListResponse = response.body();
                if (imageArrayListResponse != null) {
                    ArrayList<Images> imagesArrayList = imageArrayListResponse.getArrayList();
                    if (imagesArrayList.size() > 0) {
                        MutableLiveData.setValue(imagesArrayList);
                    }
                } else {
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageArrayListResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return MutableLiveData;
    }

    public MutableLiveData<String> addFavorite(final Context context, String id_user, String id_product) {

        final MutableLiveData<String> MutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().addFavorite(id_user, id_product);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return MutableLiveData;
    }

    public MutableLiveData<String> deleteFavorite(final Context context, String id_user, String id_product) {

        final MutableLiveData<String> MutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().deleteFavorite(id_user, id_product);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return MutableLiveData;
    }

    public MutableLiveData<String> getFavorite(final Context context, String id_user, String id_product) {

        final MutableLiveData<String> MutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().getFavorite(id_user, id_product);
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
                    Toast.makeText(context, "Field_get_items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return MutableLiveData;
    }


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
