package joseoliva.com.photogallerypro.bbdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagenes_table")
data class Imagenes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "uri")
    val uri: String
)