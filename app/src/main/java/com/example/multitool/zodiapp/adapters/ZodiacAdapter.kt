package com.example.multitool.zodiapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.zodiapp.data.Zodiac

class ZodiacAdapter(val items:List<Zodiac> = listOf()) : RecyclerView.Adapter<ZodiacViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_zodiac, parent, false)
        return ZodiacViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ZodiacViewHolder, position: Int) {
        holder.render(items[position])
    }

}

class ZodiacViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val zodiacImageView:ImageView = view.findViewById(R.id.zodiacImageView)
    val zodiacSign:TextView = view.findViewById(R.id.zodiacSign)
    val zodiacDate:TextView = view.findViewById(R.id.zodiacDate)

    fun render(zodiac: Zodiac) {
        val context:Context = itemView.context

        zodiacSign.text = context.getString(zodiac.name)

        zodiacDate.text = context.getString(zodiac.date)

        zodiacImageView.setImageResource(zodiac.image)

    }

}