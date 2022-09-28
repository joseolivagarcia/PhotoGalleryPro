package joseoliva.com.photogallerypro.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Tabs::class),
    version = 2)

abstract class TabsDataBase: RoomDatabase() {

    abstract fun getTabsDao(): TabsDao

    companion object{
        private var INSTANCE: TabsDataBase? = null
        fun getDataBase(context: Context): TabsDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TabsDataBase::class.java,
                    "tabs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}