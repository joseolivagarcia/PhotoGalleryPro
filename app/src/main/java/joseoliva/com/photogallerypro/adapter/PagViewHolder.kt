package joseoliva.com.photogallerypro.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.objects.ImageController

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val textoPrueba = view.findViewById<TextView>(R.id.tvtextopruebas)
    val imagen = view.findViewById<ImageView>(R.id.ivfotografia)

    fun render(paginaImagen: Imagenes){
        textoPrueba.setText(paginaImagen.descripcion)
        val imageUri = ImageController.getImageUri(textoPrueba.context,0) //obtengo la imagen a la que hace referencia el uri de este item

        //cargo la foto con Picasso para que no bajarla el tamaño

        Picasso.with(textoPrueba.context)
            .load(Uri.parse(imageUri.toString()))
            .resize(720,1200)
            .into(imagen)


        //imagen.setImageURI(Uri.parse(paginaImagen.uri))
    }

}
