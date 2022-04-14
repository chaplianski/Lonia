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
        fun ShortClick(question: String, comment: String, questionId: String, answer: Int)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
     //   Log.d("My Log", "Answers adapter - questions: $questions")
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionValue.text = questions[position].question
        holder.itemView.setOnClickListener {
            shortOnClickListener?.ShortClick(
                questions[position].question,
                questions[position].comment,
                questions[position].questionid,
                questions[position].answer
            )
        }
    }

    override fun getItemCount(): Int {
     //   Log.d("My Log", "Answers adapter - questions list size: $questions.size")
        return questions.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionValue: TextView = itemView.findViewById(R.id.tv_question_item_content)
    }
}