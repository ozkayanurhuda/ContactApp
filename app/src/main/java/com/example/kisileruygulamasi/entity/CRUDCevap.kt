package com.example.kisileruygulamasi.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//3
//crud cevaptakiler main de kayıt
data class CRUDCevap(@SerializedName("success")
                     @Expose
                     var success:Int,
                     @SerializedName("message")
                     @Expose
                     var message:String){

}