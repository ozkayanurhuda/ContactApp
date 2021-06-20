package com.example.kisileruygulamasi.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//5
//direk sınıf ismiyle erişme kotlin static
//companion object
class RetrofitClient {

    //retrof ayarları sonuc geri dönsün diye
    companion object{
        fun getClient(baseUrl:String):Retrofit{
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}