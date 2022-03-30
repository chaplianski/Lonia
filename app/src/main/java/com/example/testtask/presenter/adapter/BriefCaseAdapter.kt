package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.BriefCase

class BriefCaseAdapter(briefCases: List<BriefCase>): RecyclerView.Adapter<BriefCaseAdapter.ViewHolder>() {

    private val briefCases = briefCases as MutableList<BriefCase>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.briefcase_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(briefCases[position])
    }

    override fun getItemCount(): Int {
        return briefCases.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){
        val itemVessel: TextView = itemView.findViewById(R.id.tv_briefcase_item_vessel)
        val itemPort: TextView = itemView.findViewById(R.id.tv_briefcase_item_port)
        val itemInspectionSource: TextView = itemView.findViewById(R.id.tv_briefcase_item_inspection_source)
        val itemQuestion: TextView = itemView.findViewById(R.id.tv_briefcase_item_question)

    fun onBind(briefCase: BriefCase){
        itemVessel.text = briefCase.vessel
        itemPort.text = briefCase.port
        itemInspectionSource.text = briefCase.inspector
        itemQuestion.text = briefCase.question

    }
    }

}