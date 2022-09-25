package joseoliva.com.photogallerypro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import joseoliva.com.photogallerypro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}