package joseoliva.com.photogallerypro.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.adapter.ViewPagerAdapter
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel


class Fragment2 : Fragment() {

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

        //añado manualmente para la prueba
        viewModel.deleteImagenes()
        viewModel.addImagen(Imagenes(0,"cosas"))
        viewModel.addImagen(Imagenes(0,"fotos"))
        viewModel.addImagen(Imagenes(0,"bolis"))
        viewModel.addImagen(Imagenes(0,"papel"))
        listaImagenes = viewModel.imagen

        //hago lo que tenga que hacer a continuacion
        //observo la lista de imagenes para añadirlas cuando sea necesario
        viewModel.imagen.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        fab.setOnClickListener {
            Toast.makeText(context,"Pulsado", Toast.LENGTH_SHORT).show()
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