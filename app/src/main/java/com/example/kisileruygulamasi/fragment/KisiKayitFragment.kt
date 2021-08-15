package com.example.kisileruygulamasi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.databinding.FragmentKisiKayitBinding
import com.example.kisileruygulamasi.viewmodel.KisiKayitFragmentViewModel


class KisiKayitFragment : Fragment() {

    private lateinit var tasarim:FragmentKisiKayitBinding
    //vm mbağlama
    private lateinit var viewModel:KisiKayitFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_kisi_kayit, container, false)
        tasarim.kisiKayitFragment=this
        tasarim.kisiKayitToolbarBaslik="Kişi Kayıt"
        return tasarim.root
    }

    //vm bağlama onCreate içinde
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:KisiKayitFragmentViewModel by viewModels()
        viewModel=temp
    }

    fun buttonKaydet(kisi_ad:String, kisi_tel:String) {
        Log.e("Kişi Kayıt","$kisi_ad- $kisi_tel")
        viewModel.kayit(kisi_ad,kisi_tel)

    }
}