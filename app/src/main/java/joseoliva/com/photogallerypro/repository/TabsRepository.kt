package joseoliva.com.photogallerypro.repository

import android.media.Image
import androidx.lifecycle.LiveData
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.bbdd.TabsDao

class TabsRepository(private val tabsDao: TabsDao) {

    val tabs: LiveData<Tabs> = tabsDao.getAllTabs()
    val imagenes: LiveData<List<Imagenes>> = tabsDao.getAllImagenes()

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