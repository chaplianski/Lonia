package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class InspectionSourceAdapter (private val inspector: List<String>):
    RecyclerView.Adapter<InspectionSourceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.inspection_source_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.inspectorSource.text = inspector[position]
    }

    override fun getItemCount(): Int {
        return inspector.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val inspectorSource: TextView = itemView.findViewById(R.id.tv_inspection_source_name)
    }
}