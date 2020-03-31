package com.iavariav.kbmonline.rest;

import com.iavariav.kbmonline.model.ErrorModel;
import com.iavariav.kbmonline.model.LoginModel;
import com.iavariav.kbmonline.model.MobilModel;
import com.iavariav.kbmonline.model.PemesananModel;
import com.iavariav.kbmonline.model.UserModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api_login.php")
    Call<LoginModel> login(@Query("NIK_USER") String username,
                           @Query("PASSWORD_USER") String password);


    @GET("api_get.php")
    Call<ArrayList<PemesananModel>> getAllData(
            @Query("change") String change
    );
    @GET("api_get.php")
    Call<ArrayList<PemesananModel>> getDataHistoriAtasan(
            @Query("change") String change,
            @Query("ID_USER_ATASAN") String idUser
    );
    @GET("api_get.php")
    Call<ArrayList<UserModel>> getDataUser(
            @Query("change") String change
    );

    @GET("api_get.php")
    Call<ArrayList<MobilModel>> getDataMobil(
            @Query("change") String change
    );

    @GET("api_get.php")
    Call<ArrayList<PemesananModel>> getDataUserHistoriPemesanan(
            @Query("change") String change,
            @Query("NAMA_PEMESAN") String NAMA_PEMESAN
    );

    @GET("api_get.php")
    Call<ArrayList<MobilModel>> getDataMobilByStatus(
            @Query("change") String change,
            @Query("TYPE_MOBIL") String TYPE_MOBIL
    );

    @FormUrlEncoded
    @POST("user/api_tambah_registrasi.php")
    Call<ErrorModel> postRegisterUser(
            @Field("NAMA_USER") String NAMA_USER,
            @Field("NIK_USER") String NIK_USER,
            @Field("PASSWORD_USER") String PASSWORD_USER,
            @Field("REG_ID") String REG_ID,
            @Field("KEY_ENCRYPT") String KEY_ENCRYPT
    );

    @FormUrlEncoded
    @POST("approvel/api_update_aprovel.php")
    Call<ResponseBody> updateStatusPemesanan(
            @Field("ID_PEMESANAN") String idPesanan,
            @Field("ID_USER_ATASAN") String idUserAtasan,
            @Field("STATUS_PEMESANAN") String status
    );
    @FormUrlEncoded
    @POST("api_update_reg_id.php")
    Call<ResponseBody> updateRegID(
            @Field("REG_ID") String regID,
            @Field("ID_USER") String idUser);

    @FormUrlEncoded
    @POST("user/api_tambah_pemesanan.php")
    Call<ResponseBody> postDataPemesanan(
            @Field("ID_USER_ATASAN") String ID_USER_ATASAN,
            @Field("NAMA_PEMESAN") String NAMA_PEMESAN,
            @Field("JENIS_KEPERLUAN") String JENIS_KEPERLUAN,
            @Field("JENIS_PEMESANAN") String JENIS_PEMESANAN,
            @Field("JENIS_KENDARAAN") String JENIS_KENDARAAN,
            @Field("KEBERANGKATAN_KAWASAN") String KEBERANGKATAN_KAWASAN,
            @Field("KEBERANGKATAN_WITEL") String KEBERANGKATAN_WITEL,
            @Field("KEBERANGKATAN_AREA_POOL") String KEBERANGKATAN_AREA_POOL,
            @Field("TUJUAN_ALAMAT_JEMPUT") String TUJUAN_ALAMAT_JEMPUT,
            @Field("TUJUAN_AREA") String TUJUAN_AREA,
            @Field("TUJUAN_ALAMAT_DETAIL_MAPS") String TUJUAN_ALAMAT_DETAIL_MAPS,
            @Field("LAT_AWAL") String LAT_AWAL,
            @Field("LONG_AWAL") String LONG_AWAL,
            @Field("LAT_TUJUAN") String LAT_TUJUAN,
            @Field("LONG_TUJUAN") String LONG_TUJUAN,
            @Field("WAKTU_KEBERANGKATAN") String WAKTU_KEBERANGKATAN,
            @Field("WAKTU_KEPULANGAN") String WAKTU_KEPULANGAN,
            @Field("NO_TELEPON_KANTOR") String NO_TELEPON_KANTOR,
            @Field("NO_HP") String NO_HP,
            @Field("JUMLAH_PENUMPANG") String JUMLAH_PENUMPANG,
            @Field("ISI_PENUMPANG") String ISI_PENUMPANG,
            @Field("KETERANGAN") String KETERANGAN,
            @Field("JARAK_PER_KM") String JARAK_PER_KM,
//            @Field("BENSIN_PER_LITER") String BENSIN_PER_LITER,
            @Field("NAMA_ATASAN") String NAMA_ATASAN,
            @Field("REG_TOKEN_PEMESANAN") String REG_TOKEN_PEMESANAN,
            @Field("REG_ID") String REG_ID
    );


    @FormUrlEncoded
    @POST("approvel/api_edit_pemesanan.php")
    Call<ResponseBody> updatePemesanan(
            @Field("JENIS_KEPERLUAN") String JENIS_KEPERLUAN,
            @Field("JENIS_PEMESANAN") String JENIS_PEMESANAN,
            @Field("JENIS_KENDARAAN") String JENIS_KENDARAAN,
            @Field("KEBERANGKATAN_KAWASAN") String KEBERANGKATAN_KAWASAN,
            @Field("KEBERANGKATAN_WITEL") String KEBERANGKATAN_WITEL,
            @Field("KEBERANGKATAN_AREA_POOL") String KEBERANGKATAN_AREA_POOL,
            @Field("TUJUAN_ALAMAT_JEMPUT") String TUJUAN_ALAMAT_JEMPUT,
            @Field("TUJUAN_AREA") String TUJUAN_AREA,
            @Field("TUJUAN_ALAMAT_DETAIL_MAPS") String TUJUAN_ALAMAT_DETAIL_MAPS,
            @Field("LAT_AWAL") String LAT_AWAL,
            @Field("LONG_AWAL") String LONG_AWAL,
            @Field("LAT_TUJUAN") String LAT_TUJUAN,
            @Field("LONG_TUJUAN") String LONG_TUJUAN,
            @Field("WAKTU_KEBERANGKATAN") String WAKTU_KEBERANGKATAN,
            @Field("WAKTU_KEPULANGAN") String WAKTU_KEPULANGAN,
            @Field("NO_TELEPON_KANTOR") String NO_TELEPON_KANTOR,
            @Field("NO_HP") String NO_HP,
            @Field("JUMLAH_PENUMPANG") String JUMLAH_PENUMPANG,
            @Field("ISI_PENUMPANG") String ISI_PENUMPANG,
            @Field("KETERANGAN") String KETERANGAN,
            @Field("JARAK_PER_KM") String JARAK_PER_KM,
            @Field("BENSIN_PER_LITER") String BENSIN_PER_LITER,
            @Field("REG_TOKEN_PEMESANAN") String REG_TOKEN_PEMESANAN
    );

//    @GET("api_get.php")
//    Call<ArrayList<PelaporModel>> getHistory(
//            @Query("change") String change,
//            @Query("id_user_validator") String idUser
//    );
//


    @GET("firebase")
    Call<ResponseBody> postDataNotif(
            @Query("title") String title,
            @Query("message") String message,
            @Query("push_type") String push_type,
            @Query("regId") String regId

    );


}
