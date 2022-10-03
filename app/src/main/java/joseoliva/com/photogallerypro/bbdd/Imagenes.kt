package joseoliva.com.photogallerypro.bbdd

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity(tableName = "imagenes_table")
data class Imagenes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "descripcion")
    val descripcion: String,
    @ColumnInfo(name = "uri")
    val uri: String,
    @ColumnInfo(name = "codigotab")
    val codigotab: Int
)