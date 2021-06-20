package com.example.kisileruygulamasi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.databinding.CardTasarimBinding
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.fragment.AnasayfaFragmentDirections
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar


//context ekle mutlaka(olmasa da olur )
//implement constructors deyip 3ini de ekle
//veri kümesi için adapter
//sil için sona ekledik viewmodel
class KisilerAdapter(var mContext: Context, var kisilerListesi:List<Kisiler>, var viewModel: AnasayfaFragmentViewModel)
    : RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>() {

    //cartasarimi temsilen inner class for binding
    inner class CardTasarimTutucu(cardTasarimBinding: CardTasarimBinding)
        : RecyclerView.ViewHolder(cardTasarimBinding.root) {
            var cardTasarim:CardTasarimBinding
            //bağlama
            init {
                this.cardTasarim=cardTasarimBinding
            }
    }

    //tasarimi temsil ediyor dememiz lazım
    //tasarımla bağlama görsel nesnelre erişme
    //her tasarimda aynı değişmez
    //görsel nesne yönetme
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater=LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

//--------------------------TIKLAYINCA OLACAK DEĞİŞİMLER-------------------
    //özgü işlemler her yerde değişir
    //kisilerde 3 kisi nesnesi var. o satırlardaki bilgileri bunla gösterir
    //kaç elmean varsa o kadar çalışır
    //0 sa ilk kisi vs positiion sırayla adı ve teli yazar
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kisi = kisilerListesi[position]
        holder.cardTasarim.kisiNesnesi=kisi
        //holder.cardTasarim.textViewKisiBilgi.text = "${kisi.kisi_ad} - ${kisi.kisi_tel}"
        //tıklayınca detay sayfaya geçiş olucak
        holder.cardTasarim.satirCard.setOnClickListener {
            //Toast.makeText(mContext, "${kisi.kisi_ad} seçildi.", Toast.LENGTH_SHORT).show()

           val gecis = AnasayfaFragmentDirections.detayGecis(kisi)

            //sayfa gcişi it = holder.cardTasarim.satirCard
            //R.id.detayaGecis sadece gecis oldu
           Navigation.findNavController(it).navigate(gecis)
        }
//---------------------------------POPUP MENU---------------------------------
        //view for popup
        //3 noktaya basıldıgında
        holder.cardTasarim.imageViewSil.setOnClickListener {
            Snackbar.make(it, "${kisi.kisi_ad} silmek istiyor musunuz ?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.sil(kisi.kisi_id)
                    Snackbar.make(it, "${kisi.kisi_ad} silindi.", Snackbar.LENGTH_LONG).show()
                }.show()
        }
    }

    //veri kümesinde kaç eleman var
    //kaç satır var vs
    override fun getItemCount(): Int {
        return kisilerListesi.size
    }
}