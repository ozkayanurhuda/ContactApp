package com.example.kisileruygulamasi.retrofit

import com.example.kisileruygulamasi.entity.CRUDCevap
import com.example.kisileruygulamasi.entity.KisilerCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//3
//tüm kişiler çalışınca o webservisi çalıştırcak
//cevaptaki kişileri liste olarak versin ve success bilgil versim

interface KisilerDaoInterface {
    @GET("kisiler/tum_kisiler.php")
    fun tumKisiler(): Call<KisilerCevap>

//-------------------insert
    @POST("kisiler/insert_kisiler.php")
    //sıkıntı düzeltiyor
    @FormUrlEncoded
    //gödereceklerim, cevap crud cevap olacak
    fun kisiEkle(@Field("kisi_ad") kisi_ad:String,
                 @Field("kisi_tel") kisi_tel:String):Call<CRUDCevap>

//---------------------delete
    @POST("kisiler/delete_kisiler.php")
    //sıkıntı düzeltiyor
    @FormUrlEncoded
    //gödereceklerim, cevap crud cevap olacak
    fun kisiSil(@Field("kisi_id") kisi_id:Int):Call<CRUDCevap>

//-------------------guncelle
    @POST("kisiler/update_kisiler.php")
    //sıkıntı düzeltiyor
    @FormUrlEncoded
    //gödereceklerim, cevap crud cevap olacak
    fun kisiGuncelle(@Field("kisi_id") kisi_id:Int,
                     @Field("kisi_ad") kisi_ad:String,
                     @Field("kisi_tel") kisi_tel:String):Call<CRUDCevap>
//-----------------------arama
//cevabı merak ediyoruz.tüm kisiler gibi bir cevap donucek. sonuc bu formatta oldugu için
    @POST("kisiler/tum_kisiler_arama.php")
    //sıkıntı düzeltiyor
    @FormUrlEncoded
    //gödereceklerim, cevap crud cevap olacak
    fun kisiAra(@Field("kisi_ad") kisi_ad: String):Call<KisilerCevap>



}