package joseoliva.com.photogallerypro.bbdd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.google.android.material.tabs.TabLayout

@Dao
interface TabsDao {

    @Query("Select * From tabs_table")
    fun getAllTabs(): LiveData<Tabs>

    @Insert
    suspend fun insertTabs(tab: Tabs)

    @Query("Delete From tabs_table")
    suspend fun deleteTabs()
}