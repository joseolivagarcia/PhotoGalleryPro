package joseoliva.com.photogallerypro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.onboardingscreen.OnboardingItem

class OnboardingItemsAdapter(private val onboardingItems: List<OnboardingItem>): RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    inner class OnboardingItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        //inicio los datos a rellenar del item y los relleno con lo que haya en cada uno

        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageOnBoarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setImageResource(onboardingItem.onboardingImage)
            textTitle.text = onboardingItem.tittle
            textDescription.text = onboardingItem.description
        }
    }
}