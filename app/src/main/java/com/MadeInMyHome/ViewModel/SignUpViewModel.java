package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.R;
import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class SignUpViewModel extends ViewModel {


    public MutableLiveData<String> signUp(final Context context, EditText email, EditText pass,
                                          EditText f_name, EditText l_name, EditText date,
                                          EditText gender, String phone, String encodedImage) {

        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();

        Call<ResultUserResponse> call = RestClient.getService()
                .signUp( email.getText().toString(), pass.getText().toString(),
                        f_name.getText().toString(), l_name.getText().toString(),
                        date.getText().toString(), gender.getText().toString(),
                        phone ,encodedImage);

        call.enqueue(new Callback<ResultUserResponse>() {
            @Override
            public void onResponse(Call<ResultUserResponse> call, Response<ResultUserResponse> response) {
                ResultUserResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                        String id = data.getId();
                        userMutableLiveData.setValue(id);

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
