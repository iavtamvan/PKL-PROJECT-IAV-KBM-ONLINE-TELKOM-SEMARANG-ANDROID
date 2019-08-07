package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.model.ErrorModel;
import com.iavariav.kbmonline.model.MobilModel;
import com.iavariav.kbmonline.rest.ApiConfig;
import com.iavariav.kbmonline.rest.ApiService;
import com.iavariav.kbmonline.ui.atasan.adapter.MobilAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobilPresenter {
    private ArrayList<MobilModel> mobilModels;
    private MobilAdapter mobilAdapter;

    public void getDataMobil(final Context context, String change, final RecyclerView recyclerView){
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
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<MobilModel>> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}