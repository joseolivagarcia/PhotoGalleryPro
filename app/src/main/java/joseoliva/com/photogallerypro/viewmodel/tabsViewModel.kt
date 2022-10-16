package joseoliva.com.photogallerypro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.bbdd.Tabs
import joseoliva.com.photogallerypro.bbdd.TabsDataBase
import joseoliva.com.photogallerypro.repository.TabsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class tabsViewModel(application: Application): AndroidViewModel(application) {

    val repository: TabsRepository
    var tab: LiveData<Tabs>
    var imagenestotal: LiveData<List<Imagenes>>
    var imagentab0: LiveData<List<Imagenes>>
    var imagentab1: LiveData<List<Imagenes>>
    var imagentab2: LiveData<List<Imagenes>>
    var imagentab3: LiveData<List<Imagenes>>

    init {
        val dao = TabsDataBase.getDataBase(application).getTabsDao()
        repository = TabsRepository(dao)
        tab = repository.tabs
        imagenestotal = repository.imagenestotal
        imagentab0 = repository.imagenestab0
        imagentab1 = repository.imagenestab1
        imagentab2 = repository.imagenestab2
        imagentab3 = repository.imagenestab3
    }

    fun addTabs(tab: Tabs) = viewModelScope.launch(Dispatchers.IO){
        repository.insertTabs(tab)
    }

    fun deleteTabs() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTabs()
    }

    fun addImagen(imagen: Imagenes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertImagenes(imagen)
    }

    fun deleteAllImagenes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteImagenes()
    }
}