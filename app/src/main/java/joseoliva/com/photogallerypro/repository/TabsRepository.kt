package joseoliva.com.photogallerypro.repository

import androidx.lifecycle.LiveData
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.bbdd.TabsDao

class TabsRepository(private val tabsDao: TabsDao) {

    val tabs: LiveData<Tabs> = tabsDao.getAllTabs()

    suspend fun insertTabs(tab: Tabs){
        tabsDao.insertTabs(tab)
    }

    suspend fun deleteTabs(){
        tabsDao.deleteTabs()
    }
}