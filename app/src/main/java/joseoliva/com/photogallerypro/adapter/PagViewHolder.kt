package joseoliva.com.photogallerypro.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.squareup.picasso.Picasso
import joseoliva.com.photogallerypro.MainActivity
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.bbdd.TabsDataBase
import joseoliva.com.photogallerypro.objects.ImageController

class PagViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val textoPrueba = view.findViewById<TextView>(R.id.tvtextopruebas)
    val imagen = view.findViewById<ImageView>(R.id.ivfotografia)
    val btnborrar = view.findViewById<ImageView>(R.id.icodelete)

    fun render(paginaImagen: Imagenes){

        textoPrueba.setText(paginaImagen.descripcion)
        val imageUri = ImageController.getImageUri(textoPrueba.context,paginaImagen.id.toLong()) //obtengo la imagen a la que hace referencia el uri de este item

        //cargo la foto con Picasso para que no bajarla el tamaño

        Picasso.with(textoPrueba.context)
            .load(Uri.parse(imageUri.toString()))
            .into(imagen)

        //imagen.setImageURI(Uri.parse(paginaImagen.uri))

        btnborrar.setOnClickListener {
            val dialog = AlertDialog.Builder(textoPrueba.context)
                .setMessage("Quieres eliminar ésta página?")
                .setNegativeButton("NO"){
                        view, _ -> view.dismiss()
                }
                .setPositiveButton("SI") { view, _ ->
                    view.dismiss()
                    //referencio la base de datos para poder usarla donde me interese
                    val db: TabsDataBase = Room.databaseBuilder(textoPrueba.context,TabsDataBase::class.java,"tabs_database").allowMainThreadQueries().build()
                    val fotoABorrar = db.getTabsDao().getById(paginaImagen.id)
                    db.getTabsDao().deleteImagen(fotoABorrar)
                    val intent = Intent(textoPrueba.context,MainActivity::class.java)
                    textoPrueba.context.startActivity(intent)
                }
                .setCancelable(false)
                .create()

            dialog.show()

        }
    }
}


