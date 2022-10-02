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
    var imagen: LiveData<List<Imagenes>>

    init {
        val dao = TabsDataBase.getDataBase(application).getTabsDao()
        repository = TabsRepository(dao)
        tab = repository.tabs
        imagen = repository.imagenes
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

    fun deleteImagenes() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteImagenes()
    }
}