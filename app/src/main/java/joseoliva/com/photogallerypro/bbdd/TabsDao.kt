package joseoliva.com.photogallerypro.bbdd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.google.android.material.tabs.TabLayout

@Dao
interface TabsDao {

    //para la tabla Tabs
    @Query("Select * From tabs_table")
    fun getAllTabs(): LiveData<Tabs>

    @Insert
    suspend fun insertTabs(tab: Tabs)

    @Query("Delete From tabs_table")
    suspend fun deleteTabs()

    //para la tabla Imagenes
    @Query("Select * From imagenes_table")
    fun getAllImagenes(): LiveData<List<Imagenes>>

    @Query("Select * From imagenes_table Where codigotab = :codigo")
    fun getAllImagenesByCodigo(codigo: Int): LiveData<List<Imagenes>>

    @Insert
    suspend fun insertImagen(imagen: Imagenes)

    @Delete
    suspend fun deleteImagen(imagen: Imagenes)

    @Query("Delete From imagenes_table")
    suspend fun deleteImagenes()
}