package com.example.kisileruygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.CRUDCevap
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.entity.KisilerCevap
import com.example.kisileruygulamasi.retrofit.ApiUtils
import com.example.kisileruygulamasi.retrofit.KisilerDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisilerdaoRepos {

    //veri tabanından veri getiirp viewmodele sunar

    private val kisilerListesi:MutableLiveData<List<Kisiler>>
    private val kdaoi: KisilerDaoInterface

    init {
        kdaoi=ApiUtils.getKisilerDaoInterface()
        kisilerListesi= MutableLiveData()
    }

    //kisilerListesinden db den veri alıp aktarmak amacımız
    fun kisileriGetir ():MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    //liste bilgisini aktar
    fun tumKisileriAl() {
        kdaoi.tumKisiler().enqueue(object: Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>?, response: Response<KisilerCevap>) {
                //kisileri alma cevap liste olucak
                //hatadn ikinci soru işaerti silindi
                val liste=response.body().kisiler
                kisilerListesi.value=liste
            }
            override fun onFailure(call: Call<KisilerCevap>?, t: Throwable?) {
            }
        })
    }

    fun kisiKayit(kisi_ad:String, kisi_tel:String) {
        kdaoi.kisiEkle(kisi_ad,kisi_tel).enqueue(object: Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                Log.e("Başarı", response.body().success.toString())
                Log.e("Mesaj", response.body().message)
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun kisiSil(kisi_id:Int) {
        kdaoi.kisiSil(kisi_id).enqueue(object: Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                //listeyi güncelle
                tumKisileriAl()
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun kisiGuncelle(kisi_id:Int,kisi_ad: String, kisi_tel: String) {
        kdaoi.kisiGuncelle(kisi_id,kisi_ad,kisi_tel).enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    // içinde t geçen isimleri getircek searching case sensitive değil
    fun kisiAra(aramaKelimesi:String) {
        kdaoi.kisiAra(aramaKelimesi).enqueue(object: Callback<KisilerCevap> {
            override fun onResponse(call: Call<KisilerCevap>?, response: Response<KisilerCevap>) {
                //kisileri alma cevap liste olucak
                //hatadn ikinci soru işaerti silindi
                val liste=response.body().kisiler
                //logcatte gördük listeyi
                kisilerListesi.value=liste

            }
            override fun onFailure(call: Call<KisilerCevap>?, t: Throwable?) {
            }
        })
    }


}