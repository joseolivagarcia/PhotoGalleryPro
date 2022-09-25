package joseoliva.com.photogallerypro

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import joseoliva.com.photogallerypro.databinding.ActivityMainBinding
import joseoliva.com.photogallerypro.fragments.Fragment0
import joseoliva.com.photogallerypro.fragments.Fragment1
import joseoliva.com.photogallerypro.fragments.Fragment2
import joseoliva.com.photogallerypro.fragments.Fragment3

class MainActivity : AppCompatActivity() {

    //referencio el contenedor de los tabs
    lateinit var tabscontainer: TabLayout
    /*Creo un array que sera en el que pondre las opciones
    que el usuario configure. Por defecto saldran 4 opciones
     */
    lateinit var opcionesTabs: MutableList<String>

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

        //inicializo el array de opciones de tabs con opciones por defecto
        opcionesTabs = mutableListOf("Amigos", "Vacaciones","Familia","Selfies")
        //y añado las opciones a los tabs
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(0)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(1)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(2)))
        tabscontainer.addTab(tabscontainer.newTab().setText(opcionesTabs.get(3)))
        //pongo el tabcontainer como scroolable por si no caben.
        //Fixed seria si tengo pocos tabs y se que no se van a salir de la pantalla
        tabscontainer.setTabMode(TabLayout.MODE_SCROLLABLE)

        //cargamos el fragment principal (el de casa real que es el primer tab)
        openFragment(Fragment0())

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
                    }
                    3 -> {
                        openFragment(Fragment3())
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.prueba -> {
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