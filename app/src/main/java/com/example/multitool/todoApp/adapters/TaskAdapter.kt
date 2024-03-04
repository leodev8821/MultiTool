package com.example.multitool.todoApp.adapters

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.databinding.ItemTodoappBinding
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO

class TaskAdapter(
    private var items:List<Task> = listOf(),
    val onClickListener: (position:Int) -> Unit,
    val onCheckBoxListener: (position:Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>() {

        public fun updateData(items:List<Task>) {
            this.items = items
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTodoappBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(items[position])
        holder.itemView.setOnClickListener{ onClickListener(position)}
        holder.binding.itemDoneCheckBox.setOnCheckedChangeListener{ which, isChecked ->
            onCheckBoxListener(position)
        }
    }

}

class TaskViewHolder(val binding:ItemTodoappBinding) : RecyclerView.ViewHolder(binding.root){

    fun render(task: Task){
        val dateFormat = DateFormat.format("dd-MMMM-yyyy", task.date)
        binding.itemTaskTextView.text = task.task
        binding.itemCategoryTextView.text = task.category
        binding.itemDateTextView.text = dateFormat
        binding.itemDoneCheckBox.isChecked = task.done
    }
}
