package com.example.testtask.presenter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions

class AnswersAdapter (private val questions: List<Questions>): RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(answer: Int)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionItem.text = questions[position].question
        holder.questionItem.setOnClickListener {
            shortOnClickListener?.ShortClick(
                questions[position].answer
            )
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val questionItem: TextView = itemView.findViewById(R.id.tv_answer_item_content)
    }

}