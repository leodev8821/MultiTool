package com.example.multitool.todoApp.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.databinding.ItemCategoryBinding
import com.example.multitool.todoApp.database.Category

class CategoryAdapter(
    private var items:List<Category> = listOf(),
    val onEditButtonClick: (position:Int) -> Unit,) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener { onEditButtonClick(position) }
    }

    fun updateData(items:List<Category>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){

    fun render(category: Category){
        binding.categoryTextView.text = category.category
        binding.editFoatingActionButton
        binding.eraseFoatingActionButton
    }
}