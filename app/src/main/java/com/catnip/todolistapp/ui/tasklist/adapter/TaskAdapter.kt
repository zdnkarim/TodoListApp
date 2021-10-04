package com.catnip.todolistapp.ui.tasklist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.todolistapp.data.model.Task
import com.catnip.todolistapp.databinding.ItemTaskBinding


class TaskAdapter(
    private val itemClick: (Task, Int) -> Unit,
    private val itemLongClick: (Task, Int) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    var items: List<Task> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // creating view for item in recyclerview
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClick, itemLongClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemTaskBinding,
        private val itemClick: (Task, Int) -> Unit,
        private val itemLongClick: (Task, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Task, position: Int) {
            with(item) {
                binding.tvTitleTask.text = title
                itemView.setOnClickListener {
                    itemClick(item, position)
                }
                itemView.setOnLongClickListener {
                    itemLongClick(item, position)
                    true
                }
            }
        }

    }
}