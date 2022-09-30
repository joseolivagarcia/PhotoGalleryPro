package joseoliva.com.photogallerypro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.databinding.ActivityConfigBinding
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel

class ConfigActivity : AppCompatActivity() {

    lateinit var binding: ActivityConfigBinding
    lateinit var viewModel: tabsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ettab1 = binding.etTab1
        val ettab2 = binding.etTab2
        val ettab3 = binding.etTab3
        val ettab4 = binding.etTab4
        val btn = binding.btnguardar

        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(tabsViewModel::class.java)

        btn.setOnClickListener {
            //recojo los textos de los edittext
            val tab1 = ettab1.text.toString()
            val tab2 = ettab2.text.toString()
            val tab3 = ettab3.text.toString()
            val tab4 = ettab4.text.toString()
            //creo un nuevo Tabs (modelo de la bbdd) con ellos
            val newTabs = Tabs(0,tab1,tab2,tab3,tab4)
            //borro el Tabs que pudiera estar en la bbdd
            viewModel.deleteTabs()
            //guardo en la bbdd el nuevo que he configurado
            viewModel.addTabs(newTabs)
            //vuelvo a la main activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}