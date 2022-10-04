package joseoliva.com.photogallerypro.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Imagenes

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val textoPrueba = view.findViewById<TextView>(R.id.tvtextopruebas)
    val imagen = view.findViewById<ImageView>(R.id.ivfotografia)

    fun render(paginaImagen: Imagenes){
        textoPrueba.setText(paginaImagen.descripcion)
        imagen.setImageURI(Uri.parse(paginaImagen.uri))
    }

}
