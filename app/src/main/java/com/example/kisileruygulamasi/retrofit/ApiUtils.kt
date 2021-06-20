package com.example.kisileruygulamasi.retrofit


//6
//interf deki metodlara erişilir. en önemli ksıım
//baseurl buraya yazılır
//kalan kısım interfacee yazılır

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getKisilerDaoInterface(): KisilerDaoInterface {
            return RetrofitClient.getClient(BASE_URL)
                    .create(KisilerDaoInterface::class.java)
        }


    }
}