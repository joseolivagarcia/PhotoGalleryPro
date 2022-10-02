package joseoliva.com.photogallerypro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.photogallerypro.R
import joseoliva.com.photogallerypro.bbdd.Imagenes

class ViewPagerAdapter(val lista: List<Imagenes>): RecyclerView.Adapter<PagViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PagViewHolder(layoutInflater.inflate(R.layout.item_imagen,parent,false))
    }

    override fun onBindViewHolder(holder: PagViewHolder, position: Int) {

        val item = lista[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return lista.size
    }
}


