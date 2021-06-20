package com.example.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.repo.KisilerdaoRepos

class AnasayfaFragmentViewModel:ViewModel() {
    //live data tanımı dinleme işlemi

    var kisilerListesi= MutableLiveData<List<Kisiler>>()
    val kdaor=KisilerdaoRepos()
  //bilgiyi aktarma

    init {
        kisilerYukle()
        kisilerListesi=kdaor.kisileriGetir()
    }

    fun kisilerYukle ()  {
        kdaor.tumKisileriAl()

    }

    fun ara (aramaKelimesi:String)  {
        kdaor.kisiAra(aramaKelimesi)

    }

    fun sil(kisi_id:Int) {
        kdaor.kisiSil(kisi_id)
    }
}