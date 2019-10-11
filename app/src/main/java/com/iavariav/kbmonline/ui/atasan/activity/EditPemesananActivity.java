package com.iavariav.kbmonline.ui.atasan.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.snackbar.Snackbar;
import com.iavariav.kbmonline.R;
import com.iavariav.kbmonline.helper.Config;
import com.iavariav.kbmonline.model.MobilModel;
import com.iavariav.kbmonline.rest.ApiConfig;
import com.iavariav.kbmonline.rest.ApiService;
import com.iavariav.kbmonline.ui.user.fragment.PemesananMobilFragment;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

import im.delight.android.location.SimpleLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPemesananActivity extends AppCompatActivity {

    private String jeniskeperluan[] = {"-- PILIH --", "Reguler", "Sosial", "Event", "CAM", "Emergency", "Penanganan Gangguan", "Direksi"};
    private String jenisPemesanan[] = {"-- PILIH --", "MOBIL", "MOBIL & SOPIR", "SOPIR"};
    private String kawasan[] = {"-- PILIH --", "JABAR-BANTEN", "JABODETABEK", "JATENG-DIY", "JATIM-BALI-NUSA", "KALIMANTAN", "PAMASULA"};
    private String witel[] = {"-- PILIH --", "Jateng Barut", "Jateng Barsel", "Jateng Utara", "Jateng Timur", "Jateng Tengah", "Jateng Selatan", "DI YOgyakarta", "Jateng Timsel"};
    private String areaPool[] = {"-- PILIH --", "SMG Johar", "SMG Pahlawan"};
    private String areaTujuanKawasan[] = {"-- PILIH --", "JABAR-BANTEN", "JABODETABEK", "JATENG-DIY", "JATIM-BALI-NUSA", "KALIMANTAN", "PAMASULA"};
    private String areaTujuanKawasanPilhan[] = {"-- PILIH --", "KS Sudirman", "MGL Yos Sudarso", "PK Merak", "PK Pemuda", "PWT Merdeka", "SLA Diponegoro", "SMH Johar", "SMG Pahlawan", "SLO Mayor Kusmanto", "Yogyakarta", "Lainnya"};
    private String jumlahIsiPenumpang[] = {"-- PILIH --", "1", "2", "3", "4", "5"};

    private String android_id;
    private String namaPemesan;
    private String idUser;
    private String regID;

    private String placeNameAdress;
    private String placeName;

    private String jeniskeperluanSave;
    private String jenisPemesananSave;
    private String jenisPemesananMobilSave;
    private String kawasanSave;
    private String witelSave;
    private String areaPoolSave;
    private String areaTujuanKawasanSave;
    private String areaTujuanKawasanPilhanSave;
    private String jumlahIsiPenumpangSave;

    private String jenis_keperluan;
    private String jenis_pemesanan;
    private String jenis_kendaraan;
    private String keberangkatan_kawasan;
    private String keberangkatan_witel;
    private String keberangkatan_area_pool;
    private String tujuan_alamat_jemput;
    private String tujuan_area;
    private String tujuan_alamat_detail_maps;
    private String lat_awal;
    private String long_awal;
    private String lat_tujuan;
    private String long_tujuan;
    private String waktu_keberangkatan;
    private String waktu_kepulangan;
    private String no_telepon_kantor;
    private String no_hp;
    private String jumlah_penumpang;
    private String isi_penumpang;
    private String keterangan;
    private String jarak_per_km;
    private String bensin_per_liter;
    private String reg_token_pemesanan;

    private final static int PLACE_PICKER_REQUEST = 999;

    private double latitudeBerangkat;
    private double longitudeBerangkat;
    private double latitudeTujuan;
    private double longitudeTUjuan;
    private double distance;
    private double hitungJarak;
    double stringJarak;
    double hitungHargaBBM;
    private SimpleLocation location;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener dateBerangkat;
    private DatePickerDialog.OnDateSetListener dateKepulangan;

    private MaterialSpinner spnJenisKeperluan;
    private MaterialSpinner spnJenisPemesanan;
    private MaterialSpinner spnJenisMobil;
    private MaterialSpinner spnKawasan;
    private MaterialSpinner spnWitel;
    private MaterialSpinner spnAreaPool;
    private EditText edtPenjemputan;
    private MaterialSpinner spnAreaTujuanKawasan;
    private MaterialSpinner spnAreaTujuan;
    private TextView tvAlamatDetail;
    private Button btnLocDetail;
    private MaterialSpinner spnJumlahPenumpang;
    private EditText edtIsiPenumpang;
    private LinearLayout divTanggal;
    private TextView tvTanggal;
    private TextView tvKetukTanggalHide;
    private LinearLayout divWaktu;
    private TextView tvWaktu;
    private TextView tvKetukWaktuHide;
    private LinearLayout divTanggalKepulangan;
    private TextView tvTanggalKepulangan;
    private TextView tvKetukTanggalHideKepulangan;
    private LinearLayout divWaktuKepulangan;
    private TextView tvWaktuKepulangan;
    private TextView tvKetukWaktuHideKepulangan;
    private EditText edtNoTeleponKantor;
    private EditText edtNoHp;
    private EditText edtKeterangan;
    private TextView tvNamaAtasan;
    private TextView tvTokenPemesanan;
    private Button btnPesanSekarang;

    private PemesananMobilFragment pemesananMobilFragment;
    private ArrayList<MobilModel> mobilModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pemesanan);
        initView();

        jenis_keperluan = getIntent().getStringExtra(Config.BUNDLE_JENIS_KEPERLUAN);
        jenis_pemesanan = getIntent().getStringExtra(Config.BUNDLE_JENIS_PEMESANAN);
        jenis_kendaraan = getIntent().getStringExtra(Config.BUNDLE_JENIS_KENDARAAN);
        keberangkatan_kawasan = getIntent().getStringExtra(Config.BUNDLE_KEBERANGKATAN_KAWASAN);
        keberangkatan_witel = getIntent().getStringExtra(Config.BUNDLE_KEBERANGKATAN_WITEL);
        keberangkatan_area_pool = getIntent().getStringExtra(Config.BUNDLE_KEBERANGKATAN_AREA_POOL);
        tujuan_alamat_jemput = getIntent().getStringExtra(Config.BUNDLE_TUJUAN_ALAMAT_JEMPUT);
        tujuan_area = getIntent().getStringExtra(Config.BUNDLE_TUJUAN_AREA);
        tujuan_alamat_detail_maps = getIntent().getStringExtra(Config.BUNDLE_TUJUAN_ALAMAT_DETAIL_MAPS);
        lat_awal = getIntent().getStringExtra(Config.BUNDLE_LAT_AWAL);
        long_awal = getIntent().getStringExtra(Config.BUNDLE_LONG_AWAL);
        lat_tujuan = getIntent().getStringExtra(Config.BUNDLE_LAT_TUJUAN);
        long_tujuan = getIntent().getStringExtra(Config.BUNDLE_LONG_TUJUAN);
        waktu_keberangkatan = getIntent().getStringExtra(Config.BUNDLE_WAKTU_KEBERANGKATAN);
        waktu_kepulangan = getIntent().getStringExtra(Config.BUNDLE_WAKTU_KEPULANGAN);
        no_telepon_kantor = getIntent().getStringExtra(Config.BUNDLE_NO_TELEPON_KANTOR);
        no_hp = getIntent().getStringExtra(Config.BUNDLE_NO_HP);
        jumlah_penumpang = getIntent().getStringExtra(Config.BUNDLE_JUMLAH_PENUMPANG);
        isi_penumpang = getIntent().getStringExtra(Config.BUNDLE_ISI_PENUMPANG);
        keterangan = getIntent().getStringExtra(Config.BUNDLE_KETERANGAN);
        jarak_per_km = getIntent().getStringExtra(Config.BUNDLE_JARAK_PER_KM);
        bensin_per_liter = getIntent().getStringExtra(Config.BUNDLE_BENSIN_PER_LITER);
        reg_token_pemesanan = getIntent().getStringExtra(Config.BUNDLE_REG_TOKEN_PEMESANAN);

        mobilModels = new ArrayList<>();
        pemesananMobilFragment = new PemesananMobilFragment();
        location = new SimpleLocation(EditPemesananActivity.this);
        // if we can't access the location yet
        if (!location.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(EditPemesananActivity.this);
        }
        latitudeBerangkat = location.getLatitude();
        longitudeBerangkat = location.getLongitude();
//        Toast.makeText(this, "" + latitudeBerangkat + longitudeBerangkat, Toast.LENGTH_SHORT).show();

        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Random r = new Random();
        int random = (r.nextInt(44) * 88);
        tvTokenPemesanan.setText("TELKOM-KBM" + random + "-" + android_id);
        tvNamaAtasan.setText("Yani Maria Christie");

        myCalendar = Calendar.getInstance();
        dateBerangkat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                pemesananMobilFragment.updateLabelBerangkat();
            }
        };
        dateKepulangan = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                pemesananMobilFragment.updateLabelKepulangan();
            }
        };

        divTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesananMobilFragment.getDate(dateBerangkat);

            }
        });

        divWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesananMobilFragment.getTime(tvWaktu);
            }
        });


        divTanggalKepulangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesananMobilFragment.getDate(dateKepulangan);
            }
        });
        divWaktuKepulangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pemesananMobilFragment.getTime(tvWaktuKepulangan);
            }
        });

        btnLocDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Objects.requireNonNull(EditPemesananActivity.this)), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        tvAlamatDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri navigationIntentUri = Uri.parse("google.navigation:q=" + latitudeTujuan + "," + longitudeTUjuan);//creating intent with latlng
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        spnJenisKeperluan.setItems(jeniskeperluan);
        spnJenisKeperluan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                jeniskeperluanSave = item;
                Snackbar.make(view, "Memilih " + jeniskeperluanSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnJenisPemesanan.setItems(jenisPemesanan);
        spnJenisPemesanan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, final int position, long id, String item) {
                jenisPemesananSave = item;
                Snackbar.make(view, "Memilih " + jenisPemesananSave, Snackbar.LENGTH_LONG).show();

                ApiService apiService = ApiConfig.getApiService();
                apiService.getDataMobilByStatus("getDataMobilByType", jenisPemesananSave)
                        .enqueue(new Callback<ArrayList<MobilModel>>() {
                            @Override
                            public void onResponse(Call<ArrayList<MobilModel>> call, Response<ArrayList<MobilModel>> response) {
                                if (response.isSuccessful()) {
                                    mobilModels = response.body();
                                    Toast.makeText(EditPemesananActivity.this, "" + mobilModels, Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < mobilModels.size(); i++) {
                                        spnJenisMobil.setItems(mobilModels.get(i).getTYPEMOBIL() + " " + mobilModels.get(i).getPLATMOBIL());

                                    }

                                    spnJenisMobil.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                                        @Override
                                        public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                                            Toast.makeText(EditPemesananActivity.this, "" + item, Toast.LENGTH_SHORT).show();
                                            jenisPemesananMobilSave = item;
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<MobilModel>> call, Throwable t) {
                                Toast.makeText(EditPemesananActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        spnKawasan.setItems(kawasan);
        spnKawasan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                kawasanSave = item;
                Snackbar.make(view, "Memilih " + kawasanSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnWitel.setItems(witel);
        spnWitel.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                witelSave = item;
                Snackbar.make(view, "Memilih " + witelSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnAreaPool.setItems(areaPool);
        spnAreaPool.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                areaPoolSave = item;
                Snackbar.make(view, "Memilih " + areaPoolSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnAreaTujuanKawasan.setItems(areaTujuanKawasan);
        spnAreaTujuanKawasan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                areaTujuanKawasanSave = item;
                Snackbar.make(view, "Memilih " + areaTujuanKawasanSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnAreaTujuan.setItems(areaTujuanKawasanPilhan);
        spnAreaTujuan.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                areaTujuanKawasanPilhanSave = item;
                Snackbar.make(view, "Memilih " + areaTujuanKawasanPilhanSave, Snackbar.LENGTH_LONG).show();
            }
        });
        spnJumlahPenumpang.setItems(jumlahIsiPenumpang);
        spnJumlahPenumpang.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                jumlahIsiPenumpangSave = item;
                Snackbar.make(view, "Memilih " + jumlahIsiPenumpangSave, Snackbar.LENGTH_LONG).show();
            }
        });

        btnPesanSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initView() {
        spnJenisKeperluan = findViewById(R.id.spn_jenis_keperluan);
        spnJenisPemesanan = findViewById(R.id.spn_jenis_pemesanan);
        spnJenisMobil = findViewById(R.id.spn_jenis_mobil);
        spnKawasan = findViewById(R.id.spn_kawasan);
        spnWitel = findViewById(R.id.spn_witel);
        spnAreaPool = findViewById(R.id.spn_area_pool);
        edtPenjemputan = findViewById(R.id.edt_penjemputan);
        spnAreaTujuanKawasan = findViewById(R.id.spn_area_tujuan_kawasan);
        spnAreaTujuan = findViewById(R.id.spn_area_tujuan);
        tvAlamatDetail = findViewById(R.id.tv_alamat_detail);
        btnLocDetail = findViewById(R.id.btn_loc_detail);
        spnJumlahPenumpang = findViewById(R.id.spn_jumlah_penumpang);
        edtIsiPenumpang = findViewById(R.id.edt_isi_penumpang);
        divTanggal = findViewById(R.id.div_tanggal);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvKetukTanggalHide = findViewById(R.id.tv_ketuk_tanggal_hide);
        divWaktu = findViewById(R.id.div_waktu);
        tvWaktu = findViewById(R.id.tv_waktu);
        tvKetukWaktuHide = findViewById(R.id.tv_ketuk_waktu_hide);
        divTanggalKepulangan = findViewById(R.id.div_tanggal_kepulangan);
        tvTanggalKepulangan = findViewById(R.id.tv_tanggal_kepulangan);
        tvKetukTanggalHideKepulangan = findViewById(R.id.tv_ketuk_tanggal_hide_kepulangan);
        divWaktuKepulangan = findViewById(R.id.div_waktu_kepulangan);
        tvWaktuKepulangan = findViewById(R.id.tv_waktu_kepulangan);
        tvKetukWaktuHideKepulangan = findViewById(R.id.tv_ketuk_waktu_hide_kepulangan);
        edtNoTeleponKantor = findViewById(R.id.edt_no_telepon_kantor);
        edtNoHp = findViewById(R.id.edt_no_hp);
        edtKeterangan = findViewById(R.id.edt_keterangan);
        tvNamaAtasan = findViewById(R.id.tv_nama_atasan);
        tvTokenPemesanan = findViewById(R.id.tv_token_pemesanan);
        btnPesanSekarang = findViewById(R.id.btn_pesan_sekarang);
    }
}
