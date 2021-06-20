package com.example.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.repo.KisilerdaoRepos

class KisiKayitFragmentViewModel:ViewModel() {
    //
    val kdaor= KisilerdaoRepos()
    fun kayit(kisi_ad:String, kisi_tel:String) {
        kdaor.kisiKayit(kisi_ad,kisi_tel)
    }
}