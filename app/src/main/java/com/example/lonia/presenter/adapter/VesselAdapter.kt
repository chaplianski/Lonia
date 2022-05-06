package com.example.lonia.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R

class VesselAdapter(private val vessels: List<String>) :
    RecyclerView.Adapter<VesselAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(item: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vessel_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vesseItem.text = vessels[position]
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(vessels[position])

        }
    }

    override fun getItemCount(): Int {
        return vessels.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val vesseItem: TextView = itemView.findViewById(R.id.tv_vessel_name)
    }
}