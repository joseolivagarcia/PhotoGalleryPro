package joseoliva.com.photogallerypro.fragments

import android.app.Application
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import joseoliva.com.photogallerypro.AddPictureActivity
import joseoliva.com.photogallerypro.MainActivity
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.adapter.ViewPagerAdapter
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel


class Fragment0 : Fragment() {

    var mView: View? = null
    lateinit var viewModel: tabsViewModel

    lateinit var listaImagenes: LiveData<List<Imagenes>>
    lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_0, container, false)

        fab = mView!!.findViewById(R.id.fab)
        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.applicationContext as Application)
        ).get(tabsViewModel::class.java)

        //viewModel.deleteImagenes()
        listaImagenes = viewModel.imagentab0

        //hago lo que tenga que hacer a continuacion
        //observo la lista de imagenes para añadirlas cuando sea necesario
        viewModel.imagentab0.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        fab.setOnClickListener {
            val intent = Intent(context,AddPictureActivity::class.java)
            intent.putExtra("codigotab", 0) //paso el codigotab, en este caso 0 para luego identificar las imagenes de cada tab
            startActivity(intent)
        }

        return mView
    }

    private fun initRecyclerView(lista: List<Imagenes>) {
        val viewpager = mView?.findViewById<ViewPager2>(R.id.viewPager)
        if (viewpager != null) {
            viewpager.adapter = ViewPagerAdapter(lista)
        }
    }

}