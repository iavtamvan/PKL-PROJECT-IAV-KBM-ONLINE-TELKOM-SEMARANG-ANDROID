package com.iavariav.kbmonline.ui.atasan.presenter;

import android.content.Context;
import android.widget.Toast;

import com.iavariav.kbmonline.rest.ApiConfig;
import com.iavariav.kbmonline.rest.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPemesananPresenter {
    public void editPemesanan(
            final Context context,
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
            String BENSIN_PER_LITER,
            String REG_TOKEN_PEMESANAN
    ) {
        ApiService apiService = ApiConfig.getApiService();
        apiService.updatePemesanan(
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
                BENSIN_PER_LITER,
                REG_TOKEN_PEMESANAN
        )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, ""+ response.body(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
