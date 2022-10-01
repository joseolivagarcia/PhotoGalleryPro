package joseoliva.com.photogallerypro

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.databinding.ActivityConfigBinding
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel

class ConfigActivity : AppCompatActivity() {

    lateinit var binding: ActivityConfigBinding
    lateinit var viewModel: tabsViewModel
    lateinit var estilo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //referencio los radiobutton
        val rbele = binding.rbelegante
        val rbale = binding.rbalegre
        val rbdiver = binding.rbdivertido
        val rbmelan = binding.rbmelancolico

        //recibo los textos que tengo en los tabs si ya los he configurado alguna vez y el estilo
        val tab1recibido = intent.getStringExtra("tab1")
        val tab2recibido = intent.getStringExtra("tab2")
        val tab3recibido = intent.getStringExtra("tab3")
        val tab4recibido = intent.getStringExtra("tab4")
        estilo = intent.getStringExtra("estilo").toString()

        val ettab1 = binding.etTab1
        val ettab2 = binding.etTab2
        val ettab3 = binding.etTab3
        val ettab4 = binding.etTab4
        val btn = binding.btnguardar

        //pongo los textos de los tabs recibidos(si los he recibido) y el estilo
        ettab1.setText(tab1recibido)
        ettab2.setText(tab2recibido)
        ettab3.setText(tab3recibido)
        ettab4.setText(tab4recibido)
        when(estilo){
            "Elegante" ->{
                rbele.isChecked = true
            }
            "Alegre" ->{
                rbale.isChecked = true
            }
            "Divertido" ->{
                rbdiver.isChecked = true
            }
            "Melancolico" ->{
                rbmelan.isChecked = true
            }
        }

        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(tabsViewModel::class.java)

        btn.setOnClickListener {
            //recojo los textos de los edittext y el estilo
            val tab1 = ettab1.text.toString()
            val tab2 = ettab2.text.toString()
            val tab3 = ettab3.text.toString()
            val tab4 = ettab4.text.toString()
            //compruebo que he rellenado los 4 campos
            if(!tab1.isEmpty() && !tab2.isEmpty() && !tab3.isEmpty() && !tab4.isEmpty()){
                //reocojo el estilo seleccionado
                //guardo el estilo que este en el checkbox
                val rbelegante = binding.rbelegante
                if (rbelegante.isChecked){
                    estilo = "Elegante"
                }
                val rbalegre = binding.rbalegre
                if (rbalegre.isChecked){
                    estilo = "Alegre"
                }
                val rbdivertido = binding.rbdivertido
                if (rbdivertido.isChecked){
                    estilo = "Divertido"
                }
                val rbmelan = binding.rbmelancolico
                if (rbmelan.isChecked){
                    estilo = "Melancolico"
                }
                //creo un nuevo Tabs (modelo de la bbdd) con ellos
                val newTabs = Tabs(0,tab1,tab2,tab3,tab4,estilo)
                //borro el Tabs que pudiera estar en la bbdd
                viewModel.deleteTabs()
                //guardo en la bbdd el nuevo que he configurado
                viewModel.addTabs(newTabs)
                //vuelvo a la main activity
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Rellena todos los tabs",Toast.LENGTH_SHORT).show()
            }

        }
    }
}