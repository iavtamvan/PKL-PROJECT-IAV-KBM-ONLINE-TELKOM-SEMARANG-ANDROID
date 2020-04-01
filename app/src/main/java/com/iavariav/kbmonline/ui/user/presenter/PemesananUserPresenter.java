package com.iavariav.kbmonline.ui.user.presenter;

import android.content.Context;
import android.widget.Toast;

import com.iavariav.kbmonline.rest.serverUpgris.ApiConfig;
import com.iavariav.kbmonline.rest.serverUpgris.ApiService;
import com.iavariav.kbmonline.ui.user.UserActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananUserPresenter {

    public void dataUserPemesanan(
            final Context context,
            String ID_USER_ATASAN,
            String NAMA_PEMESAN,
            String JENIS_KEPERLUAN,
            String JENIS_PEMESANAN,
            String JENIS_KENDARAAN,
            String KEBERANGKATAN_KAWASAN,
            String KEBERANGKATAN_WITEL,
            String KEBERANGKATAN_AREA_POOL,
            String TUJUAN_ALAMAT_JEMPUT,
            String TUJUAN_AREA,
            String TUJUAN_ALAMAT_DETAIL_MAPS,
            String LAT_AWAL,
            String LONG_AWAL,
            String LAT_TUJUAN,
            String LONG_TUJUAN,
            String WAKTU_KEBERANGKATAN,
            String WAKTU_KEPULANGAN,
            String NO_TELEPON_KANTOR,
            String NO_HP,
            String JUMLAH_PENUMPANG,
            String ISI_PENUMPANG,
            String KETERANGAN,
            String JARAK_PER_KM,
//            String BENSIN_PER_LITER,
            String NAMA_ATASAN,
            String REG_TOKEN_PEMESANAN,
            String REG_ID
    ){

        ApiService apiService = ApiConfig.getApiService();
        apiService.postDataPemesanan(
                ID_USER_ATASAN,
                NAMA_PEMESAN,
                JENIS_KEPERLUAN,
                JENIS_PEMESANAN,
                JENIS_KENDARAAN,
                KEBERANGKATAN_KAWASAN,
                KEBERANGKATAN_WITEL,
                KEBERANGKATAN_AREA_POOL,
                TUJUAN_ALAMAT_JEMPUT,
                TUJUAN_AREA,
                TUJUAN_ALAMAT_DETAIL_MAPS,
                LAT_AWAL,
                LONG_AWAL,
                LAT_TUJUAN,
                LONG_TUJUAN,
                WAKTU_KEBERANGKATAN,
                WAKTU_KEPULANGAN,
                NO_TELEPON_KANTOR,
                NO_HP,
                JUMLAH_PENUMPANG,
                ISI_PENUMPANG,
                KETERANGAN,
                JARAK_PER_KM,
//                BENSIN_PER_LITER,
                NAMA_ATASAN,
                REG_TOKEN_PEMESANAN,
                REG_ID
        )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                String error = jsonObject.optString("error_msg");
                                if (error.equalsIgnoreCase("Berhasil")){
                                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                                    ((UserActivity)context).setupHistori();
                                } else {
                                    Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
