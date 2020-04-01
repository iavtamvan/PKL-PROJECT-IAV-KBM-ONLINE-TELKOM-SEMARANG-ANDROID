package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.model.MobilModel;
import com.iavariav.kbmonline.rest.serverUpgris.ApiConfig;
import com.iavariav.kbmonline.rest.serverUpgris.ApiService;
import com.iavariav.kbmonline.ui.atasan.adapter.MobilAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobilPresenter {
    private ArrayList<MobilModel> mobilModels;
    private MobilAdapter mobilAdapter;

    public void getDataMobil(final Context context, String change, final RecyclerView recyclerView, final LinearLayout div){
        mobilModels = new ArrayList<>();
        ApiService apiService = ApiConfig.getApiService();
        apiService.getDataMobil(change)
                .enqueue(new Callback<ArrayList<MobilModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<MobilModel>> call, Response<ArrayList<MobilModel>> response) {
                        if (response.isSuccessful()){
                            mobilModels = response.body();
                            mobilAdapter = new MobilAdapter(mobilModels, context);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(mobilAdapter);
                            mobilAdapter.notifyDataSetChanged();

                            if (mobilModels.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                div.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                div.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MobilModel>> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
