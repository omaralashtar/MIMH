package com.MadeInMyHome.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.MadeInMyHome.WebService.RestClientEmail;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessageViewModel extends ViewModel {
    public MutableLiveData<String> sendMessage(final Context context, String toEmail, String message) {


        final MutableLiveData<String> userMutableLiveData = new MutableLiveData<>();


        Call<ResponseBody> call = RestClientEmail.getService().sendEmail("mailgun@sandbox70d6b319b8eb46e79fc7cb1b990a187c.mailgun.org",
                toEmail, "Welcome in Made In My Home", message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody data = response.body();
                if (data != null) {
                    try {
                        JSONObject obj = new JSONObject(data.toString());
                        if (obj.get("messages")!=null) {
                            userMutableLiveData.setValue(obj.getJSONArray("message").get(0)+"");

                        } else {
                            Toast.makeText(context, obj.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(context,"no data" , Toast.LENGTH_SHORT).show();
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
