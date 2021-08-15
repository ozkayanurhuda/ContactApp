package com.example.kisileruygulamasi.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.adapter.KisilerAdapter
import com.example.kisileruygulamasi.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel


class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var adapter:KisilerAdapter
    private lateinit var viewModel:AnasayfaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment=this
        tasarim.anasayfaToolbarBaslik="Kişiler"
        //toolbar için
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        //viewmodeldeki live data dinleme
        //adapterdan viewmodeldaki func lara wrişim için adaptera param olarak viewmodel ekledik (silme için)
        viewModel.kisilerListesi.observe(viewLifecycleOwner, { kisilerListesi->
            adapter= KisilerAdapter(requireContext(),kisilerListesi,viewModel)
            tasarim.adapter=adapter
        })
        return tasarim.root
    }

    //sayfa gecisi view ister(kisi kayıt sayfasına geçiş)
    fun fabTikla(view:View) {
        Navigation.findNavController(view).navigate(R.id.kayitGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //search işlemini gerçekleştirebilmek için
        setHasOptionsMenu(true)

        //viewmodelı bağla
        val temp:AnasayfaFragmentViewModel by viewModels()
        viewModel=temp
    }


//toolbar menuyu bağla
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    //sayfayla toolbar menuyu bağladım
        inflater.inflate(R.menu.toolbar_arama, menu)
    //itema tıklayınca aramayı tetikledim
        val item = menu.findItem(R.id.action_ara)
    //itema searchview özelliği verdim
        val searchView=item.actionView as SearchView
    //searchviewa sayfaya bağladım
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

//arama sonucları için implement -----------------------
    override fun onQueryTextSubmit(query: String): Boolean {

        Log.e("Aramaya basılsın",query)
        viewModel.ara(query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {

        Log.e("Harf girdikçe ", newText)
        viewModel.ara(newText)

        return true
    }

//her geri geldiğinde arayüzü günceller
    override fun onResume() {
        super.onResume()
        viewModel.kisilerYukle()
    }
}