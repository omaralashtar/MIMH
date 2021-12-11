package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {


    public MutableLiveData<User> signup(final Context context, EditText email, EditText pass, String encodedImage) {


        final MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();


        Call<ResultResponse> call = RestClient.getService().signUp(email.getText().toString(), pass.getText().toString(), encodedImage);
        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse data = response.body();
                if (data != null) {
                    if (data.getResult().equals("1")) {
                        User user = data.getUser();
                        userMutableLiveData.setValue(user);

                    } else {
                        Toast.makeText(context, context.getResources().getString(R.string.Field_Login_Or_Signup), Toast.LENGTH_SHORT).show();
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
