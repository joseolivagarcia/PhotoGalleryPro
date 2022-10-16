package joseoliva.com.photogallerypro

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.bbdd.TabsDataBase
import joseoliva.com.photogallerypro.databinding.ActivityMainBinding
import joseoliva.com.photogallerypro.fragments.Fragment0
import joseoliva.com.photogallerypro.fragments.Fragment1
import joseoliva.com.photogallerypro.fragments.Fragment2
import joseoliva.com.photogallerypro.fragments.Fragment3
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: tabsViewModel

    //referencio el contenedor de los tabs
    lateinit var tabscontainer: TabLayout
    //referencio el tabs(su barra) para poder cambiarle el color de fondo
    lateinit var colortabs: TabLayout
    lateinit var estilo: String //usare esta var para cambiar el estilo

    /*Creo un array que sera en el que pondre las opciones
    que el usuario configure. Por defecto saldran 4 opciones
    y otro array para guardar las opciones que esten en la bbdd
     */
    lateinit var opcionesTabs: MutableList<String>
    var tabsLista: Tabs? = null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio el toolbar mio propio
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        //inicio el tabscontainer
        tabscontainer = binding.tabs

        //inicio las var necesarias
        colortabs = binding.tabs

        //pongo el tabcontainer como scroolable por si no caben.
        //Fixed seria si tengo pocos tabs y se que no se van a salir de la pantalla
        tabscontainer.setTabMode(TabLayout.MODE_SCROLLABLE)

        /*inicializo el array de opciones de tabs con los tabs que haya en la bbdd,
        si no hay tabs guardados (porque sea la primera vez que usamos la app,
        pongo unos tabs por defecto
        */
        opcionesTabs = mutableListOf("Amigos", "Vacaciones", "Familia", "Selfies")
        //y añado las opciones a los tabs
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(0)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(1)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(2)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(3)))
        //cargamos el fragment principal
        openFragment(Fragment0())

        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(tabsViewModel::class.java)

        //observo por si cambia la var tab que la que tiene el LiveData
        viewModel.tab.observe(this) { tab ->
            tab?.let {
                tabsLista = tab
                //elimino los tabs para poner los nuevos que haya en la bbdd
                tabscontainer.removeAllTabs()
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab1))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab2))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab3))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab4))

                //pongo el estilo que venga con el Tabs
                estilo = tabsLista!!.estilo
                when(estilo){
                    "Elegante" ->{
                        colortabs.setBackgroundColor(Color.parseColor("#5E5B5B"))
                        toolbar.setBackgroundColor(Color.parseColor("#292929"))
                        colortabs.setSelectedTabIndicatorColor(Color.parseColor("#000000"))
                        //fab.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#5E5B5B"))
                    }
                    "Alegre" ->{
                        colortabs.setBackgroundColor(Color.parseColor("#ECAD51"))
                        toolbar.setBackgroundColor(Color.parseColor("#FF9800"))
                        colortabs.setSelectedTabIndicatorColor(Color.parseColor("#FA3E02"))
                    }
                    "Divertido" ->{
                        colortabs.setBackgroundColor(Color.parseColor("#9DC56E"))
                        toolbar.setBackgroundColor(Color.parseColor("#8BC34A"))
                        colortabs.setSelectedTabIndicatorColor(Color.parseColor("#01AA08"))
                    }
                    "Melancolico" ->{
                        colortabs.setBackgroundColor(Color.parseColor("#56BEED"))
                        toolbar.setBackgroundColor(Color.parseColor("#03A9F4"))
                        colortabs.setSelectedTabIndicatorColor(Color.parseColor("#0288F3"))
                    }
                    else -> {}
                }
            }

        }

        //para la escucha de los tabs (cuando los seleccionas)
        tabscontainer.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position: Int = tabscontainer.getSelectedTabPosition()
                when (position) {
                    0 -> {
                        openFragment(Fragment0())
                        return
                    }
                    1 -> {
                        openFragment(Fragment1())
                        return
                    }
                    2 -> {
                        openFragment(Fragment2())
                        return
                    }
                    3 -> {
                        openFragment(Fragment3())
                        return
                    }
                    else -> {}
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    //para poner menus en el toolbar sobreescribo estos dos metodos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //para indicar una accion al pulsar un item del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.config -> {
                val intent = Intent(this,ConfigActivity::class.java)
                intent.putExtra("tab1", tabsLista?.tab1)
                intent.putExtra("tab2", tabsLista?.tab2)
                intent.putExtra("tab3", tabsLista?.tab3)
                intent.putExtra("tab4", tabsLista?.tab4)
                intent.putExtra("estilo", tabsLista?.estilo)
                startActivity(intent)
                return true
            }
            R.id.borrar -> {
                val dialog = AlertDialog.Builder(this)
                    .setMessage("Quieres eliminar todas las fotografías?")
                    .setNegativeButton("NO"){
                            view, _ -> view.dismiss()
                    }
                    .setPositiveButton("SI") { view, _ ->
                        view.dismiss()
                        viewModel.deleteAllImagenes()

                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                    .setCancelable(false)
                    .create()
                dialog.show()
                return  true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    //funcion para cuando llamamos a un fragment (cada tab abrira su fragment correspondiente
    fun openFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {

    }
}