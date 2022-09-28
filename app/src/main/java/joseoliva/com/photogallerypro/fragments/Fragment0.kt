package joseoliva.com.photogallerypro.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel


class Fragment0 : Fragment() {

    var mView: View? = null
    lateinit var viewModel: tabsViewModel
    var tabsLista: Tabs? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater!!.inflate(R.layout.fragment_0, container, false)

        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.applicationContext as Application)
        ).get(tabsViewModel::class.java)

        val btn: Button = mView!!.findViewById(R.id.btnprueba)
        btn.setOnClickListener {
            viewModel.deleteTabs()
            viewModel.addTabs(Tabs(0, "Personales", "Mascotas", "Familia", "Amigos"))
            Toast.makeText(context,"Pulsado",Toast.LENGTH_SHORT).show()
        }

        return mView
    }

}