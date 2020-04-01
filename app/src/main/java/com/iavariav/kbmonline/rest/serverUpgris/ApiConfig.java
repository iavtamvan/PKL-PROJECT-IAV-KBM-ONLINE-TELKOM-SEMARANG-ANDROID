package com.iavariav.kbmonline.rest.serverUpgris;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static ApiService getApiService(){
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.43.76/~mac/kbm_online/")
                .baseUrl("http://sig.upgris.ac.id/api_iav/kbm_online/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
