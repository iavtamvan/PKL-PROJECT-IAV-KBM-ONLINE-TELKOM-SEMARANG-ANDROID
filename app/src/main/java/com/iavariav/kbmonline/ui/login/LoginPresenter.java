package com.iavariav.kbmonline.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.iavariav.kbmonline.helper.Config;
import com.iavariav.kbmonline.model.LoginModel;
import com.iavariav.kbmonline.rest.serverUpgris.ApiConfig;
import com.iavariav.kbmonline.rest.serverUpgris.ApiService;
import com.iavariav.kbmonline.ui.atasan.activity.AtasanActivity;
import com.iavariav.kbmonline.ui.user.UserActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginModel loginModel;

    public void login(final Context context, String username, String password){
        ApiService apiService = ApiConfig.getApiService();
        apiService.login(username, password)
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        if (response.isSuccessful()){
                            loginModel = response.body();
//                            Toast.makeText(context, "" + loginModel.getUsername(), Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Config.SHARED_PREF_ID, loginModel.getId());
                            editor.putString(Config.SHARED_PREF_NAMA_LENGKAP, loginModel.getUsername());
                            editor.putString(Config.SHARED_PREF_RULE, loginModel.getRule());
                            editor.putString(Config.SHARED_PREF_KEY_ENCRYPT, loginModel.getKey());
                            editor.apply();
                            String regID = sharedPreferences.getString("regId", "");
                            updateRegID(context, regID, loginModel.getId());

                            if (loginModel.getErrorMsg().equalsIgnoreCase("Gagal Login")){
                                Toast.makeText(context, "Periksa akun anda", Toast.LENGTH_SHORT).show();
                            } else {
                                String rule= loginModel.getRule();
                                if (rule.equalsIgnoreCase("pimpinan")){
                                    ((LoginActivity)context).finishAffinity();
                                    context.startActivity(new Intent(context, AtasanActivity.class));
                                } else if (rule.equalsIgnoreCase("user")){
                                    ((LoginActivity)context).finishAffinity();
                                    context.startActivity(new Intent(context, UserActivity.class));
//                                Toast.makeText(context, "User RUle", Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void updateRegID(final Context context, String regID, String idUser) {
        ApiService apiService = ApiConfig.getApiService();
        apiService.updateRegID(regID, idUser)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
//                            Toast.makeText(context, "Berhasil reg id", Toast.LENGTH_SHORT).show();
//                            ((LoginActivity)context).finishAffinity();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
