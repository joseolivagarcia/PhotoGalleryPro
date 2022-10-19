package joseoliva.com.photogallerypro.onboardingscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import joseoliva.com.photogallerypro.MainActivity
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.adapter.OnboardingItemsAdapter

class OnBoardingActivity : AppCompatActivity() {

    //referencio el adappter
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        setOnboardingItems()
        setupIndicators() //esta es la fun que dibuja los tres puntos
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems(){

        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                //creo los items que vaya a necesitar
                OnboardingItem(
                    onboardingImage = R.drawable.icoinstrucciones,
                    tittle = getString(R.string.itemTitle0),
                    description = getString(R.string.itemDescription0)
                ),
            OnboardingItem(
                onboardingImage = R.drawable.icosettings,
                tittle = getString(R.string.itemTitle1),
                description = getString(R.string.itemDescription1)
            ),
                OnboardingItem(
                    onboardingImage = R.drawable.icopapelera,
                    tittle = getString(R.string.itemTitle2),
                    description = getString(R.string.itemDescription2)
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.icoanadir,
                    tittle = getString(R.string.itemTitle3),
                    description = getString(R.string.itemDescription3)
                )
            )
        )
        //inicializo el viewpager y le paso el adapter
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingviewpager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        //esto es para los puntos de arriba a la izda del viewpager
        onboardingViewPager.registerOnPageChangeCallback(object:
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        //para dar funcionalidad a los botones
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if(onboardingViewPager.currentItem +1 < onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }else{
                navigateToMainActivity()
            }
        }
        findViewById<TextView>(R.id.textskip).setOnClickListener {
            navigateToMainActivity()
        }
        findViewById<MaterialButton>(R.id.buttongetstarted).setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity(){
        startActivity(Intent(applicationContext,MainActivity::class.java))
        finish()
    }

    //esta funcion es para mostrar los tres puntos en el linearlayout que tenemos arriba a la izda en el viewpager
    private fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}