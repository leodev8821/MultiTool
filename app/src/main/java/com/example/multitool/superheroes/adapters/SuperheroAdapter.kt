package com.example.multitool.superheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.databinding.ItemSuperheroBinding
import com.example.multitool.superheroes.data.Superhero
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var items:List<Superhero> = listOf(), val onClickListener: (position:Int) -> Unit) : RecyclerView.Adapter<SuperheroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<Superhero>?) {
        items = results!!
        notifyDataSetChanged()
    }

}

class SuperheroViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root){

    fun render(superhero:Superhero){
        binding.nameTextView.text = superhero.name
        Picasso.get().load(superhero.image.url).into(binding.photoImageView)
    }
}