package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class PortAdapter(private val ports: List<String>) :
    RecyclerView.Adapter<PortAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(item: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.port_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemPort.text = ports[position]
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(ports[position])
        }
    }

    override fun getItemCount(): Int {
        return ports.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemPort: TextView = itemView.findViewById(R.id.tv_port_name)
    }
}