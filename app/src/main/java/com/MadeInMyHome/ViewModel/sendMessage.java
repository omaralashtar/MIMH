package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.WebService.RestClient;
import com.MadeInMyHome.WebService.RestClientEmail;
import com.MadeInMyHome.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendMessage extends ViewModel
{
    public MutableLiveData<String> sendMessage(final Context context, EditText email, EditText pass) {


        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();


        Call<ResponseBody> call = RestClientEmail.getService().sendEmail("super200055@gmail.com",
                "ashtrr55@gmail.com","hi","tetetetyyy");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody data = response.body();
                if (data != null) {
                    try {
                        JSONObject obj = new JSONObject(data.toString());
                        if (obj.getString("message").equals("Queued. Thank you.")) {
                            userMutableLiveData.setValue(obj.getString("message"));

                        } else {
                            Toast.makeText(context, "Field_Login_Or_Signup", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    } {
                        Toast.makeText(context, "Field_Login_Or_Signup", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return userMutableLiveData;
    }

}
