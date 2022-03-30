package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class CategoryAdapter (private val categories: List<String>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryItem.text = categories[position]
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val categoryItem: TextView = itemView.findViewById(R.id.tv_category_item_content)
    }
}