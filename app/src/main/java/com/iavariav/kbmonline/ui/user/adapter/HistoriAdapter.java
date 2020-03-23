package com.iavariav.kbmonline.ui.user.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iavariav.kbmonline.R;
import com.iavariav.kbmonline.algoritma.Vigenere;
import com.iavariav.kbmonline.helper.Config;
import com.iavariav.kbmonline.model.PemesananModel;

import java.util.ArrayList;

public class HistoriAdapter extends RecyclerView.Adapter<HistoriAdapter.ViewHolder> {
    private String id, key;
    private Context context;
    private Vigenere vigenere;

    private String regToken;
    private String nama;
    private String jenisKeperluan;
    private String jenisPemesanan;
    private String km;
    private String keberangkatan;
    private String waktuKeberangkatan;
    private String tujuan;
    private String waktuTujuan;
    private String isiPenumpang;
    private String keternangan;
    private String hitungHargaBbm;

    private ArrayList<PemesananModel> pemesananModels;

    public HistoriAdapter(Context context, ArrayList<PemesananModel> pemesananModels) {
        this.context = context;
        this.pemesananModels = pemesananModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_histori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        vigenere = new Vigenere();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(Config.SHARED_PREF_ID, "");
        key = sharedPreferences.getString(Config.SHARED_PREF_KEY_ENCRYPT, "");

//        regToken = vigenere.decryptAlgorithm(pemesananModels.get(position).getREGTOKENPEMESANAN(), key);
        nama = vigenere.decryptAlgorithm(pemesananModels.get(position).getNAMAPEMESAN(), key);
        jenisKeperluan = vigenere.decryptAlgorithm(pemesananModels.get(position).getJENISKEPERLUAN(), key);
        jenisPemesanan = vigenere.decryptAlgorithm(pemesananModels.get(position).getJENISPEMESANAN(), key);
        km = vigenere.decryptAlgorithm(pemesananModels.get(position).getJARAKPERKM(), key);
        keberangkatan = vigenere.decryptAlgorithm(pemesananModels.get(position).getKEBERANGKATANAREAPOOL(), key);
        waktuKeberangkatan = vigenere.decryptAlgorithm(pemesananModels.get(position).getWAKTUKEBERANGKATAN(), key);
        tujuan = vigenere.decryptAlgorithm(pemesananModels.get(position).getTUJUANALAMATJEMPUT(), key);
        waktuTujuan = vigenere.decryptAlgorithm(pemesananModels.get(position).getWAKTUKEPULANGAN(), key);
        isiPenumpang = vigenere.decryptAlgorithm(pemesananModels.get(position).getISIPENUMPANG(), key);
        keternangan = vigenere.decryptAlgorithm(pemesananModels.get(position).getKETERANGAN(), key);
        hitungHargaBbm = vigenere.decryptAlgorithm(pemesananModels.get(position).getBENSINPERLITER(), key);

        holder.tvRegToken.setText(pemesananModels.get(position).getREGTOKENPEMESANAN() );
        holder.tvNama.setText(pemesananModels.get(position).getNAMAPEMESAN());
        holder.tvJenisKeperluan.setText(jenisKeperluan);
        holder.tvJenisPemesanan.setText(jenisPemesanan);
        holder.tvKm.setText(km + "\n KM");
        holder.tvKeberangkatan.setText(keberangkatan);
        holder.tvWaktuKeberangkatan.setText(pemesananModels.get(position).getWAKTUKEBERANGKATAN());
        holder.tvTujuan.setText(tujuan);
        holder.tvWaktuTujuan.setText(pemesananModels.get(position).getWAKTUKEPULANGAN());
        holder.tvIsiPenumpang.setText(isiPenumpang);
        holder.tvKeternangan.setText(keternangan);
        holder.tvStatus.setText(pemesananModels.get(position).getSTATUSPEMESANAN());


        String jarakKm = pemesananModels.get(position).getJARAKPERKM();
//        double hitungLiter = Integer.parseInt(jarakKm)/ 11.6;
//        double hitugHargaBBM = hitungLiter * Integer.parseInt(pemesananModels.get(position).getBENSINPERLITER());

        holder.tvHargaBbm.setText("RP." + hitungHargaBbm);


    }


    @Override
    public int getItemCount() {
        return pemesananModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRegToken;
        private TextView tvNama;
        private TextView tvJenisKeperluan;
        private TextView tvJenisPemesanan;
        private TextView tvKm;
        private TextView tvKeberangkatan;
        private TextView tvWaktuKeberangkatan;
        private TextView tvTujuan;
        private TextView tvWaktuTujuan;
        private TextView tvIsiPenumpang;
        private TextView tvKeternangan;
        private TextView tvHargaBbm;
        private TextView tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRegToken = itemView.findViewById(R.id.tv_reg_token);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJenisKeperluan = itemView.findViewById(R.id.tv_jenis_keperluan);
            tvJenisPemesanan = itemView.findViewById(R.id.tv_jenis_pemesanan);
            tvKm = itemView.findViewById(R.id.tv_km);
            tvKeberangkatan = itemView.findViewById(R.id.tv_keberangkatan);
            tvWaktuKeberangkatan = itemView.findViewById(R.id.tv_waktu_keberangkatan);
            tvTujuan = itemView.findViewById(R.id.tv_tujuan);
            tvWaktuTujuan = itemView.findViewById(R.id.tv_waktu_tujuan);
            tvIsiPenumpang = itemView.findViewById(R.id.tv_isi_penumpang);
            tvKeternangan = itemView.findViewById(R.id.tv_keternangan);
            tvHargaBbm = itemView.findViewById(R.id.tv_harga_bbm);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
