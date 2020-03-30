package com.iavariav.kbmonline.ui.atasan.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.iavariav.kbmonline.R
import com.iavariav.kbmonline.algoritma.Vigenere
import com.iavariav.kbmonline.helper.Config
import com.iavariav.kbmonline.model.PemesananModel
import com.iavariav.kbmonline.rest.ApiConfig
import com.iavariav.kbmonline.ui.atasan.activity.AtasanActivity
import com.iavariav.kbmonline.ui.atasan.activity.EditPemesananActivity

import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.util.ArrayList

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AtasanAprovalAdapter(private val context: Context, private val pemesananModels: ArrayList<PemesananModel>) : RecyclerView.Adapter<AtasanAprovalAdapter.ViewHolder>() {
    private var id: String? = null
    private var key: String? = null
    private var regId: String? = null

    private var vigenere: Vigenere? = null

    private val regToken: String? = null
    private var nama: String? = null
    private var jenisKeperluan: String? = null
    private var jenisPemesanan: String? = null
    private var km: String? = null
    private var keberangkatan: String? = null
    private var waktuKeberangkatan: String? = null
    private var tujuan: String? = null
    private var waktuTujuan: String? = null
    private var isiPenumpang: String? = null
    private var keternangan: String? = null
    private var hitungHargaBbm: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_atasan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)
//        id = sharedPreferences.getString(Config.SHARED_PREF_ID, "")
//        holder.tvRegToken.text = pemesananModels[position].regtokenpemesanan
//        holder.tvNama.text = pemesananModels[position].namapemesan
//        holder.tvJenisKeperluan.text = pemesananModels[position].jeniskeperluan
//        holder.tvJenisPemesanan.text = pemesananModels[position].jenispemesanan
//        holder.tvKm.text = pemesananModels[position].jarakperkm + "\n KM"
//        holder.tvKeberangkatan.text = pemesananModels[position].keberangkatanareapool
//        holder.tvWaktuKeberangkatan.text = pemesananModels[position].waktukeberangkatan
//        holder.tvTujuan.text = pemesananModels[position].tujuanalamatjemput
//        holder.tvWaktuTujuan.text = pemesananModels[position].waktukepulangan
//        holder.tvIsiPenumpang.text = pemesananModels[position].isipenumpang
//        holder.tvKeternangan.text = pemesananModels[position].keterangan
//        holder.tvStatus.text = pemesananModels[position].statuspemesanan
//
//        regId = pemesananModels[position].regid
//        val jarakKm = pemesananModels[position].jarakperkm
//        //        double hitungLiter = Integer.parseInt(jarakKm)/ 11.6;
//        //        double hitugHargaBBM = hitungLiter * Integer.parseInt(pemesananModels.get(position).getBENSINPERLITER());
//
//        holder.tvHargaBbm.text = "RP." + pemesananModels[position].bensinperliter
        vigenere = Vigenere()
        val sharedPreferences = context.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        id = sharedPreferences.getString(Config.SHARED_PREF_ID, "")
        key = sharedPreferences.getString(Config.SHARED_PREF_KEY_ENCRYPT, "")

//        regToken = vigenere.decryptAlgorithm(pemesananModels.get(position).getREGTOKENPEMESANAN(), key);
        nama = vigenere!!.decryptAlgorithm(pemesananModels[position].namapemesan, key)
        jenisKeperluan = vigenere!!.decryptAlgorithm(pemesananModels[position].jeniskeperluan, key)
        jenisPemesanan = vigenere!!.decryptAlgorithm(pemesananModels[position].jenispemesanan, key)
        km = vigenere!!.decryptAlgorithm(pemesananModels[position].jarakperkm, key)
        keberangkatan = vigenere!!.decryptAlgorithm(pemesananModels[position].keberangkatanareapool, key)
        waktuKeberangkatan = vigenere!!.decryptAlgorithm(pemesananModels[position].waktukeberangkatan, key)
        tujuan = vigenere!!.decryptAlgorithm(pemesananModels[position].tujuanalamatjemput, key)
        waktuTujuan = vigenere!!.decryptAlgorithm(pemesananModels[position].waktukepulangan, key)
        isiPenumpang = vigenere!!.decryptAlgorithm(pemesananModels[position].isipenumpang, key)
        keternangan = vigenere!!.decryptAlgorithm(pemesananModels[position].keterangan, key)
        hitungHargaBbm = vigenere!!.decryptAlgorithm(pemesananModels[position].bensinperliter, key)

        holder.tvRegToken.text = pemesananModels[position].regtokenpemesanan
        holder.tvNama.setText(pemesananModels[position].namapemesan)
        holder.tvJenisKeperluan.setText(jenisKeperluan)
        holder.tvJenisPemesanan.setText(jenisPemesanan)
        holder.tvKm.setText(km + "\n KM")
        holder.tvKeberangkatan.setText(keberangkatan)
        holder.tvWaktuKeberangkatan.text = pemesananModels[position].waktukeberangkatan
        holder.tvTujuan.setText(tujuan)
        holder.tvWaktuTujuan.text = pemesananModels[position].waktukepulangan
        holder.tvIsiPenumpang.setText(isiPenumpang)
        holder.tvKeternangan.setText(keternangan)
        holder.tvStatus.text = pemesananModels[position].statuspemesanan


        val jarakKm = pemesananModels[position].jarakperkm
//        double hitungLiter = Integer.parseInt(jarakKm)/ 11.6;
//        double hitugHargaBBM = hitungLiter * Integer.parseInt(pemesananModels.get(position).getBENSINPERLITER());

        holder.tvHargaBbm.text = "RP." + pemesananModels[position].bensinperliter

        // jika disetujui
        holder.ivDisetujui.setOnClickListener {
            //                Toast.makeText(context, "Disetujui" + pemesananModels.get(position).getIDPEMESANAN() + "id : " + id, Toast.LENGTH_SHORT).show();
            updateDatas(pemesananModels[position].idpemesanan, id, "APPROVED", "Pesanan anda disetujui Pimpinan")
        }

        // jika ditolak
        holder.ivDitolak.setOnClickListener { updateDatas(pemesananModels[position].idpemesanan, id, "NOT APPROVED", "Pesanan anda ditolak oleh Pimpinan") }

        holder.cvKlik.setOnClickListener {
            val intent = Intent(context, EditPemesananActivity::class.java)
            intent.putExtra(Config.BUNDLE_JENIS_KEPERLUAN, pemesananModels[position].jeniskeperluan)
            intent.putExtra(Config.BUNDLE_JENIS_PEMESANAN, pemesananModels[position].jenispemesanan)
            intent.putExtra(Config.BUNDLE_JENIS_KENDARAAN, pemesananModels[position].jeniskendaraan)
            intent.putExtra(Config.BUNDLE_KEBERANGKATAN_KAWASAN, pemesananModels[position].keberangkatankawasan)
            intent.putExtra(Config.BUNDLE_KEBERANGKATAN_WITEL, pemesananModels[position].keberangkatanwitel)
            intent.putExtra(Config.BUNDLE_KEBERANGKATAN_AREA_POOL, pemesananModels[position].keberangkatanareapool)
            intent.putExtra(Config.BUNDLE_TUJUAN_ALAMAT_JEMPUT, pemesananModels[position].tujuanalamatjemput)
            intent.putExtra(Config.BUNDLE_TUJUAN_AREA, pemesananModels[position].tujuanarea)
            intent.putExtra(Config.BUNDLE_TUJUAN_ALAMAT_DETAIL_MAPS, pemesananModels[position].tujuanalamatdetailmaps)
            intent.putExtra(Config.BUNDLE_LAT_AWAL, pemesananModels[position].latawal)
            intent.putExtra(Config.BUNDLE_LONG_AWAL, pemesananModels[position].longawal)
            intent.putExtra(Config.BUNDLE_LAT_TUJUAN, pemesananModels[position].lattujuan)
            intent.putExtra(Config.BUNDLE_LONG_TUJUAN, pemesananModels[position].longtujuan)
            intent.putExtra(Config.BUNDLE_WAKTU_KEBERANGKATAN, pemesananModels[position].waktukeberangkatan)
            intent.putExtra(Config.BUNDLE_WAKTU_KEPULANGAN, pemesananModels[position].waktukepulangan)
            intent.putExtra(Config.BUNDLE_NO_TELEPON_KANTOR, pemesananModels[position].noteleponkantor)
            intent.putExtra(Config.BUNDLE_NO_HP, pemesananModels[position].nohp)
            intent.putExtra(Config.BUNDLE_JUMLAH_PENUMPANG, pemesananModels[position].jumlahpenumpang)
            intent.putExtra(Config.BUNDLE_ISI_PENUMPANG, pemesananModels[position].isipenumpang)
            intent.putExtra(Config.BUNDLE_KETERANGAN, pemesananModels[position].keterangan)
            intent.putExtra(Config.BUNDLE_JARAK_PER_KM, pemesananModels[position].jarakperkm)
            intent.putExtra(Config.BUNDLE_BENSIN_PER_LITER, pemesananModels[position].bensinperliter)
            intent.putExtra(Config.BUNDLE_REG_TOKEN_PEMESANAN, pemesananModels[position].regtokenpemesanan)
//            context?.startActivity(intent)
        }

    }

    private fun updateDatas(id: String, idAtasan: String?, status: String, message: String) {
        val apiService = ApiConfig.getApiService()
        apiService.updateStatusPemesanan(id, idAtasan, status)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            try {
                                val jsonObject = JSONObject(response.body()!!.string())
                                Config.pushNotif(context, "Status Pemesanan", message, "individual", regId)
                                (context as AtasanActivity).setData()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun getItemCount(): Int {
        return pemesananModels.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvRegToken: TextView
        val tvNama: TextView
        val tvJenisKeperluan: TextView
        val tvJenisPemesanan: TextView
        val tvKm: TextView
        val tvKeberangkatan: TextView
        val tvWaktuKeberangkatan: TextView
        val tvTujuan: TextView
        val tvWaktuTujuan: TextView
        val tvIsiPenumpang: TextView
        val tvKeternangan: TextView
        val tvHargaBbm: TextView
        val tvStatus: TextView
        val ivDisetujui: ImageView
        val ivDitolak: ImageView
        val cvKlik: CardView

        init {
            tvRegToken = itemView.findViewById(R.id.tv_reg_token)
            tvNama = itemView.findViewById(R.id.tv_nama)
            tvJenisKeperluan = itemView.findViewById(R.id.tv_jenis_keperluan)
            tvJenisPemesanan = itemView.findViewById(R.id.tv_jenis_pemesanan)
            tvKm = itemView.findViewById(R.id.tv_km)
            tvKeberangkatan = itemView.findViewById(R.id.tv_keberangkatan)
            tvWaktuKeberangkatan = itemView.findViewById(R.id.tv_waktu_keberangkatan)
            tvTujuan = itemView.findViewById(R.id.tv_tujuan)
            tvWaktuTujuan = itemView.findViewById(R.id.tv_waktu_tujuan)
            tvIsiPenumpang = itemView.findViewById(R.id.tv_isi_penumpang)
            tvKeternangan = itemView.findViewById(R.id.tv_keternangan)
            tvHargaBbm = itemView.findViewById(R.id.tv_harga_bbm)
            tvStatus = itemView.findViewById(R.id.tv_status)
            ivDisetujui = itemView.findViewById(R.id.iv_disetujui)
            ivDitolak = itemView.findViewById(R.id.iv_ditolak)
            cvKlik = itemView.findViewById(R.id.cv_klik)
        }
    }
}
