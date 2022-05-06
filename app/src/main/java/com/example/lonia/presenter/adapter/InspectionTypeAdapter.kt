package com.example.lonia.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R

class InspectionTypeAdapter(private val inspectionType: List<String>) :
    RecyclerView.Adapter<InspectionTypeAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(item: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.inspection_type_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.inspectionTypeItem.text = inspectionType[position]
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(inspectionType[position])
        }
    }

    override fun getItemCount(): Int {
        return inspectionType.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val inspectionTypeItem: TextView = itemView.findViewById(R.id.tv_inspection_type_name)

    }
}