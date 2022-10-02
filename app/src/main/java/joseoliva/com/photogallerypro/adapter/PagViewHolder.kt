package joseoliva.com.photogallerypro.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Imagenes

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val textoPrueba = view.findViewById<TextView>(R.id.tvtextopruebas)

    fun render(paginaImagen: Imagenes){
        textoPrueba.setText(paginaImagen.uri)
    }

}
