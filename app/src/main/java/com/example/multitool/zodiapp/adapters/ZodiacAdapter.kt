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
import com.example.multitool.zodiapp.utils.SessionManager

class ZodiacAdapter(var items:List<Zodiac> = listOf(), val onClickListener: (position:Int) -> Unit) : RecyclerView.Adapter<ZodiacViewHolder>() {

    fun updateData(list :List<Zodiac>) {
        this.items = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_zodiac, parent, false)
        return ZodiacViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ZodiacViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onClickListener(position) }
    }

}

class ZodiacViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val zodiacImageView:ImageView = view.findViewById(R.id.zodiacImageView)
    val favoriteImageButton:ImageView = view.findViewById(R.id.favoriteImageButton)
    val zodiacSign:TextView = view.findViewById(R.id.zodiacSign)
    val zodiacDate:TextView = view.findViewById(R.id.zodiacDate)

    fun render(zodiac: Zodiac) {
        val context:Context = itemView.context

        zodiacSign.text = context.getString(zodiac.name)

        zodiacDate.text = context.getString(zodiac.date)

        zodiacImageView.setImageResource(zodiac.image)

        val favDrawableId = if (zodiac.id == SessionManager(context).getFavoriteZodiac()) {
            R.drawable.black_heart_svg
        } else {
            R.drawable.favorite_svg
        }
        favoriteImageButton.setImageResource(favDrawableId)

    }

}