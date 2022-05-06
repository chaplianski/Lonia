package com.example.lonia.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.domain.model.Questionnaires

class QuestionnairesAdapter(private val categories: List<Questionnaires>) :
    RecyclerView.Adapter<QuestionnairesAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(title: String, qid: Int)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryItem.text = categories[position].title
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(categories[position].title, categories[position].qid)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryItem: TextView = itemView.findViewById(R.id.tv_category_item_content)

    }
}