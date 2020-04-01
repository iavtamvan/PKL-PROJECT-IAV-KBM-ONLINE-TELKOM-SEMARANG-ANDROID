package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.model.UserModel;
import com.iavariav.kbmonline.rest.serverUpgris.ApiConfig;
import com.iavariav.kbmonline.rest.serverUpgris.ApiService;
import com.iavariav.kbmonline.ui.atasan.adapter.DataUserAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataUserPresenter {
    private DataUserAdapter dataUserAdapter;
    private ArrayList<UserModel> userModels;
    public void dataUSer(final Context context, String change, final RecyclerView recyclerView, final LinearLayout div){
        userModels = new ArrayList<>();
        ApiService apiService = ApiConfig.getApiService();
        apiService.getDataUser(change)
                .enqueue(new Callback<ArrayList<UserModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                        if (response.isSuccessful()){
                            userModels = response.body();
                            dataUserAdapter = new DataUserAdapter(context, userModels);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(dataUserAdapter);
                            dataUserAdapter.notifyDataSetChanged();

                            if (userModels.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                div.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                div.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
