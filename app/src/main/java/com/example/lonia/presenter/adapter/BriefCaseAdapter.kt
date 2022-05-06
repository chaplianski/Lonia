package com.example.lonia.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.domain.model.BriefcasesAndQuestions
import java.text.SimpleDateFormat
import java.util.*

class BriefCaseAdapter(briefCases: List<BriefcasesAndQuestions>, visibilityAnswers: Boolean) :
    RecyclerView.Adapter<BriefCaseAdapter.ViewHolder>() {

    private val briefCases = briefCases as MutableList<BriefcasesAndQuestions>
    val visibilityAns = visibilityAnswers

    interface ShortOnClickListener {
        fun ShortClick(briefCase: BriefcasesAndQuestions)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.briefcase_item, parent, false)
        if(visibilityAns){
            v.findViewById<TextView>(R.id.tv_briefcase_item_answered).visibility = View.VISIBLE
            v.findViewById<TextView>(R.id.tv_briefcase_item_total).visibility = View.VISIBLE
        }

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(briefCases[position])


        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(briefCases[position])
        }
    }

    override fun getItemCount(): Int {
        return briefCases.size
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {


        val itemVessel: TextView = itemView.findViewById(R.id.tv_briefcase_item_vessel)
        val itemPort: TextView = itemView.findViewById(R.id.tv_briefcase_item_port)
        val itemInspectionSource: TextView =
            itemView.findViewById(R.id.tv_briefcase_item_inspection_source)
        val itemQuestionnaires: TextView =
            itemView.findViewById(R.id.tv_briefcase_item_questionnaries)
        val itemDate: TextView = itemView.findViewById(R.id.tv_briefcase_item_date)
        val itemInspectionType: TextView =
            itemView.findViewById(R.id.tv_briefcase_item_inspection_type)
        val itemAnsweredQuestion: TextView = itemView.findViewById(R.id.tv_briefcase_item_answered)
        val itemCountQuestion: TextView = itemView.findViewById(R.id.tv_briefcase_item_total)


        fun onBind(briefCase: BriefcasesAndQuestions) {
            val formateDate = SimpleDateFormat("dd.MM.yyyy", Locale.US)
            itemVessel.text = briefCase.vessel
            itemPort.text = briefCase.port
            itemInspectionSource.text = briefCase.inspector
            itemQuestionnaires.text = briefCase.category
            itemDate.text = formateDate.format(briefCase.dateOfCreation)
            itemInspectionType.text = briefCase.inspectorType
            itemCountQuestion.text = "Total: ${briefCase.total}"
            itemAnsweredQuestion.text = "Answered: ${briefCase.answered}"




        }
    }

}