package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> login(final Context context, String email, String pass) {


        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();


        Call<ResultUserResponse> call = RestClient.getService().login(email, pass);
        call.enqueue(new Callback<ResultUserResponse>() {
            @Override
            public void onResponse(Call<ResultUserResponse> call, Response<ResultUserResponse> response) {
                ResultUserResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                        String email_validate = data.getEmail_validate();
                        if(email_validate.equals("1")){
                            userMutableLiveData.setValue(data.getId());
                        }else {
                            userMutableLiveData.setValue(email_validate);
                        }
                    } else {
                        Toast.makeText(context, "Field_Login_Or_Signup", Toast.LENGTH_SHORT).show();
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

}
