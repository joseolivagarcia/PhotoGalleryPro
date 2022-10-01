package joseoliva.com.photogallerypro.bbdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabs_table")
data class Tabs(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "tab1")
    val tab1: String,
    @ColumnInfo(name = "tab2")
    val tab2: String,
    @ColumnInfo(name = "tab3")
    val tab3: String,
    @ColumnInfo(name = "tab4")
    val tab4: String,
    @ColumnInfo(name = "estilo")
    val estilo: String
)
