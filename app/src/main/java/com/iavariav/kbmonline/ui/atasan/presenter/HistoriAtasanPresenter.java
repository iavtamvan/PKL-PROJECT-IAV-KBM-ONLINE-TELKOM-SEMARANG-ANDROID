package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.model.PemesananModel;
import com.iavariav.kbmonline.rest.serverUpgris.ApiConfig;
import com.iavariav.kbmonline.rest.serverUpgris.ApiService;
import com.iavariav.kbmonline.ui.atasan.adapter.AtasanHistoriAprovalAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriAtasanPresenter {
    private ArrayList<PemesananModel> pemesananModels;
    private AtasanHistoriAprovalAdapter atasanHistoriAprovalAdapter;

    public void getDatas(final Context context, String idUser, final RecyclerView recyclerView, final LinearLayout div){
        pemesananModels = new ArrayList<>();
        ApiService apiService = ApiConfig.getApiService();
        apiService.getDataHistoriAtasan("getHistoriAtasan", idUser)
                .enqueue(new Callback<ArrayList<PemesananModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<PemesananModel>> call, Response<ArrayList<PemesananModel>> response) {
                        if (response.isSuccessful()){
                            pemesananModels = response.body();
                            atasanHistoriAprovalAdapter = new AtasanHistoriAprovalAdapter(context, pemesananModels);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(atasanHistoriAprovalAdapter);
                            atasanHistoriAprovalAdapter.notifyDataSetChanged();


                            if (pemesananModels.isEmpty()) {
                                recyclerView.setVisibility(View.GONE);
                                div.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                div.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<PemesananModel>> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
