package com.example.lonia.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.domain.model.BriefcasesAndQuestions
import java.text.SimpleDateFormat
import java.util.*

class BriefCaseAdapter(
    briefCases: List<BriefcasesAndQuestions>,
) : RecyclerView.Adapter<BriefCaseAdapter.ViewHolder>() {

    private var briefCases = briefCases as MutableList<BriefcasesAndQuestions>


    interface ShortOnClickListener {
        fun ShortClick(briefCase: BriefcasesAndQuestions)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.briefcase_item, parent, false)
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

    fun updateData(list: List<BriefcasesAndQuestions>) {

        val diffCallback = BriefcaseDiffCallback(briefCases, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        briefCases = list as MutableList<BriefcasesAndQuestions>
        diffResult.dispatchUpdatesTo(this)
       }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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


            if (briefCase.total == briefCase.answered && briefCase.isVisible == false) {
               itemView.visibility = View.GONE
               itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            } else {
                itemView.visibility = View.VISIBLE
                itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            }

            if (!briefCase.isVisible){
                itemCountQuestion.visibility = View.GONE
                itemAnsweredQuestion.visibility = View.GONE
            }

            if (briefCase.isVisible){
                itemCountQuestion.visibility = View.VISIBLE
                itemAnsweredQuestion.visibility = View.VISIBLE
            }
        }
    }

    class BriefcaseDiffCallback(
        val oldList: List<BriefcasesAndQuestions>,
        val newList: List<BriefcasesAndQuestions>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].briefCaseId == newList[newItemPosition].briefCaseId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val (briefCaseId, dateOfCreation, inspector, port, inspectorName, inspectorType, vessel, category, answered, total, isVisible) = oldList[oldItemPosition]
            val (briefCaseId1, dateOfCreation1, inspector1, port1, inspectorName1, inspectorType1, vessel1, category1, answered1, total1, isVisible1) = newList[newItemPosition]

            return briefCaseId == briefCaseId1 && dateOfCreation == dateOfCreation1 &&
                    inspector == inspector1 && port == port1 && inspectorName == inspectorName1 &&
                    inspectorType == inspectorType1 && vessel == vessel1 && category == category1 &&
                    answered == answered1 && total == total1 && isVisible == isVisible1
        }
    }
}