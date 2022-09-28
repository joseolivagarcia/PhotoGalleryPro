package joseoliva.com.photogallerypro

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import joseoliva.com.photogallerypro.bbdd.Tabs
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

        //pongo el tabcontainer como scroolable por si no caben.
        //Fixed seria si tengo pocos tabs y se que no se van a salir de la pantalla
        tabscontainer.setTabMode(TabLayout.MODE_SCROLLABLE)

<<<<<<< HEAD
        /*inicializo el array de los tabs que van a aparecer por defecto la
        primera vez que abras la app. Cuando el usuario haya configurado sus
        propios tabs, ya estaran guardados en la bbdd y seran los que aparezcan
=======
        /*inicializo el array de opciones de tabs con los tabs que haya en la bbdd,
        si no hay tabs guardados (porque sea la primera vez que usamos la app,
        pongo unos tabs por defecto
>>>>>>> daed749a2474918ef184d60ed0c7fa0e1d37c9cf
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
<<<<<<< HEAD
        //una vez que haya algun objeto en la bbdd se cargaran los tabs correspondientes
=======
>>>>>>> daed749a2474918ef184d60ed0c7fa0e1d37c9cf
        viewModel.tab.observe(this) { tab ->
            tab?.let {
                tabsLista = tab
                //elimino los tabs para poner los nuevos que haya en la bbdd
                tabscontainer.removeAllTabs()
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab1))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab2))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab3))
                tabscontainer.addTab(tabscontainer.newTab().setText(tabsLista!!.tab4))
                Toast.makeText(this, "Lista Vacia", Toast.LENGTH_SHORT).show()
<<<<<<< HEAD
            }
        }
=======


            }

        }

>>>>>>> daed749a2474918ef184d60ed0c7fa0e1d37c9cf
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
            R.id.configuracion -> {
                openFragment(Fragment0())
                return true
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