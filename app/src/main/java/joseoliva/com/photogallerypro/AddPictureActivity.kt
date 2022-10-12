package joseoliva.com.photogallerypro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import joseoliva.com.photogallerypro.databinding.ActivityAddPictureBinding
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import joseoliva.com.photogallerypro.bbdd.Imagenes
import joseoliva.com.photogallerypro.objects.ImageController
import joseoliva.com.photogallerypro.viewmodel.tabsViewModel

class AddPictureActivity : AppCompatActivity() {

    lateinit var viewModel: tabsViewModel

    //creo una var para dar un codigo a la funcion de select imagen de galeria
    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null
    var idfoto = 1

    lateinit var binding: ActivityAddPictureBinding
    lateinit var ivfoto: ImageView
    lateinit var etdescripcion: TextInputEditText
    lateinit var btnguardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicio el viewmodel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(tabsViewModel::class.java)

        viewModel.imagen.observe(this){ imagen ->
            imagen?.let {
                val sizeimagenes = viewModel.imagen.value!!.size
                if(sizeimagenes != 0){
                    idfoto = sizeimagenes +1
                    Toast.makeText(this,"tamaño lista $sizeimagenes",Toast.LENGTH_SHORT).show()
                }else{
                    idfoto = 1
                    Toast.makeText(this,"tamaño lista $sizeimagenes",Toast.LENGTH_SHORT).show()
                }
            }

        }

        //recojo el codigotab que me viene del fragment
        val codigotab = intent.extras!!.getInt("codigotab")
        Toast.makeText(this,"recibo el codigotab $codigotab",Toast.LENGTH_SHORT).show()

        etdescripcion = binding.etdescripcion
        btnguardar = binding.btnguardarfoto
        //doy funcionalidad a la imagen de la camara para cuando pulse sobre el
        ivfoto = binding.ivfoto
        ivfoto.setOnClickListener {
            //llamo al objeto ImageControler y a su funcion select...
            //y le paso esta propia actividad y un codigo
            ImageController.selectPhotoFromGallery(this, SELECT_ACTIVITY)
        }

        btnguardar.setOnClickListener {
            val descripcion = etdescripcion.text.toString() //obtengo la descripcion de la imagen

            //guardo la imagen en el dispositivo movil
            imageUri?.let {
                ImageController.saveImage(this@AddPictureActivity,idfoto.toLong(),it)
                Toast.makeText(this,"guardo la foto $idfoto",Toast.LENGTH_SHORT).show()
            }
            //creo una nueva Imagen a partir de la foto y la descripcion
            val newImagen = Imagenes(0,descripcion,imageUri.toString()!!,codigotab)
            //y la guardo en la bbdd
            viewModel.addImagen(newImagen)
        }


    }
    //para que pueda obtener la imagen de la galeria debo sobreescribir el siguiente metodo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*indicamos que cuando el requestcode (con el codigo que hemos enviado, tiene una
        respuesta correcta, es decir que hemos seleccionado una foto de la galeria...
        Entonces obtenemos la uri necesaria para acceder a la imagen.
         */
        when{
            requestCode == SELECT_ACTIVITY && resultCode == Activity.RESULT_OK ->{
                imageUri = data!!.data //la uri proviene de la data del activityonResult
                //ponemos esta uri al imageview del xml
                //lo hago con Picasso para bajarle el tamaño.
                Picasso.with(this)
                    .load(imageUri)
                    .into(ivfoto)
                //ivfoto.setImageURI(imageUri)
            }
        }
    }
}