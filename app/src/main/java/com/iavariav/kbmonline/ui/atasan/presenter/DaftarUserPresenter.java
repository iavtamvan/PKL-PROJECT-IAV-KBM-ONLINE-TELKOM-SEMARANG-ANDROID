package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.widget.Toast;

import com.iavariav.kbmonline.model.ErrorModel;
import com.iavariav.kbmonline.rest.ApiConfig;
import com.iavariav.kbmonline.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarUserPresenter {
    private ErrorModel errorModel;
    public void daftarkanUser(final Context context, String namaUser, String nik, String password, String regID, String keyEncrypt){
        ApiService apiService = ApiConfig.getApiService();
        apiService.postRegisterUser(namaUser, nik, password, regID, keyEncrypt)
                .enqueue(new Callback<ErrorModel>() {
                    @Override
                    public void onResponse(Call<ErrorModel> call, Response<ErrorModel> response) {
                        if (response.isSuccessful()){
                            errorModel = response.body();
                            Toast.makeText(context, "" + errorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "" + errorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ErrorModel> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
