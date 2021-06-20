package com.example.kisileruygulamasi.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


//2
//cevap sayfam
data class KisilerCevap(@SerializedName("kisiler")
                        @Expose
                        var kisiler:List<Kisiler>,
                        @SerializedName("success")
                        @Expose
                        var kisi_ad:Int) {
}