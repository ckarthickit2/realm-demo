package com.ckarthickit.realmexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ckarthickit.realmexample.R
import com.ckarthickit.realmexample.data.entity.TodoItem

class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal val titleView: TextView = itemView.findViewById(R.id.todoTitle)
    internal val descriptionView: TextView = itemView.findViewById(R.id.todoDescription)
}

class TodoListAdapter(items: List<TodoItem>) : RecyclerView.Adapter<TodoListViewHolder>() {
    private var todoItems = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        println("creating views...")
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_todo_card, parent, false)
        return TodoListViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        println("item_count = " + todoItems.size)
        return todoItems.size
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val item = todoItems[position]
        println("binding $todoItems ...")
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
    }

}