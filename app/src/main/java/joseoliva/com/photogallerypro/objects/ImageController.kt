package joseoliva.com.photogallerypro.objects

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

/*Este objeto se encargara de tener la funcion a la que llamamos cuando
queramos usar la galeria para traer una foto
 */

object ImageController {
    /*esta es la funcion que abrira la galeria para seleccionar una foto.
    recibe una Activity(sera la actividad desde la que llamemos a la funcion,
    y un codigo que podra ser el que queramos
     */
    fun selectPhotoFromGallery(activity: Activity, code: Int){

        val intent = Intent(Intent.ACTION_PICK) //creamos una var a la que decimos que vamos a seleccionar algo
        intent.type = "image/*" //le decimos al intent que lo que vamos a seleccionar es una imagen de cualquier tipo
        activity.startActivityForResult(intent, code) //la activity que recibimos abre otra activity a la que pasamos el intent y el codigo
    }

    /*La siguiente funcion lo que hace es guardar la foto que hemos seleccionado cuando
    pulsemos el boton de guardar. La foto se guarda en un directorio de nuestro telefono
     */
    fun saveImage(context: Context, id: Long, uri: Uri){

        val file = File(context.filesDir,id.toString()) //creamos un fichero

        //necesito los bytes que tiene nuestro uri
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()!!
        //escribo los bytes en el archivo y asi ya tengo el archivo de la imagen
        file.writeBytes(bytes)
    }

    // Creamos la funcion para recuperar cada imagen antes de ponerla en la lista

    fun getImageUri(context: Context, id: Long): Uri{
        val file = File(context.filesDir, id.toString()) //recupero el fichero de la imagen

        //si el archivo existe lo ponemos, si no, ponemos el icono de la camara
        return if(file.exists()) Uri.fromFile(file)
        else Uri.parse("android.resources://joseoliva.com.photogallerypro/drawable/icocamara")
    }
}