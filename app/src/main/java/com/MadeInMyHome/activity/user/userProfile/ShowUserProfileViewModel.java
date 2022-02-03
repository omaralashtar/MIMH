package com.MadeInMyHome.activity.user.userProfile;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.Response.UserArrayListResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowUserProfileViewModel extends ViewModel {

    public MutableLiveData<User> getUserProfile(final Context context, String id) {

        final MutableLiveData<User> MutableLiveData = new MutableLiveData<>();

        Call<UserArrayListResponse> call = RestClient.getService().getUser(id);
        call.enqueue(new Callback<UserArrayListResponse>() {
            @Override
            public void onResponse(Call<UserArrayListResponse> call, Response<UserArrayListResponse> response) {
                UserArrayListResponse UserArrayListResponse = response.body();
                if (UserArrayListResponse != null) {
                    ArrayList<User> UserArrayList = UserArrayListResponse.getArrayList();
                    if (UserArrayList.size() > 0) {
                        MutableLiveData.setValue(UserArrayList.get(0));
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

    public MutableLiveData<String> updateUser(final Context context,
                                              String id,
                                              EditText f_name,
                                              EditText l_name,
                                              EditText description,
                                              EditText date,
                                              EditText location) {
        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();

        Call<ResultUserResponse> call = RestClient.getService().updateUser(
                id,
                f_name.getText().toString(),
                l_name.getText().toString(),
                description.getText().toString(),
                date.getText().toString(),
                location.getText().toString());

        call.enqueue(new Callback<ResultUserResponse>() {
            @Override
            public void onResponse(Call<ResultUserResponse> call, Response<ResultUserResponse> response) {
                ResultUserResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                        String id = data.getId();
                        userMutableLiveData.setValue(id);

                    } else {
                        Toast.makeText(context, "Field_updateUser", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultUserResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return userMutableLiveData;
    }


    public MutableLiveData<String> updateImageUser(final Context context,
                                              String id, String image) {
        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().updateUserImage(id, image);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                       // String id = data.getResult();
                        userMutableLiveData.setValue(id);

                    } else {
                        Toast.makeText(context, "Field_updateUser", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return userMutableLiveData;
    }




}
