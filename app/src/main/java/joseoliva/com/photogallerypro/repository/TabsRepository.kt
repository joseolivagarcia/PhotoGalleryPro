package joseoliva.com.photogallerypro.repository

import android.media.Image
import androidx.lifecycle.LiveData
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.bbdd.TabsDao

class TabsRepository(private val tabsDao: TabsDao) {

    val tabs: LiveData<Tabs> = tabsDao.getAllTabs()
    val imagenestotal: LiveData<List<Imagenes>> = tabsDao.getAllImagenes()
    val imagenestab0: LiveData<List<Imagenes>> = tabsDao.getAllImagenesByCodigo(0)
    val imagenestab1: LiveData<List<Imagenes>> = tabsDao.getAllImagenesByCodigo(1)
    val imagenestab2: LiveData<List<Imagenes>> = tabsDao.getAllImagenesByCodigo(2)
    val imagenestab3: LiveData<List<Imagenes>> = tabsDao.getAllImagenesByCodigo(3)

    suspend fun insertTabs(tab: Tabs){
        tabsDao.insertTabs(tab)
    }

    suspend fun deleteTabs(){
        tabsDao.deleteTabs()
    }

    suspend fun insertImagenes(imagen: Imagenes){
        tabsDao.insertImagen(imagen)
    }

    suspend fun deleteImagenes(){
        tabsDao.deleteImagenes()
    }
}