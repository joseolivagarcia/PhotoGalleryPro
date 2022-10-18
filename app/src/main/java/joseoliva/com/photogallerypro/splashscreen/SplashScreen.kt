package joseoliva.com.photogallerypro.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import joseoliva.com.photogallerypro.MainActivity
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.onboardingscreen.OnBoardingActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,OnBoardingActivity::class.java))
    }
}