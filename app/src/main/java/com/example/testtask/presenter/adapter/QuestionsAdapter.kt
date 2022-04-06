package com.example.testtask.presenter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.Questions

class QuestionsAdapter(private val questions: List<Questions>) :
    RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(question: String, comment: String, questionId: String)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionValue.text = questions[position].question
        Log.d("My Log", "questionId in QuestionsAdapter: ${questions[position].questionid}")
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(
                questions[position].question,
                questions[position].comment,
                questions[position].questionid
            )
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionValue: TextView = itemView.findViewById(R.id.tv_question_item_content)
    }
}