package com.example.testtask.presenter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.domain.model.Answers

class AnswersAdapter (private val answers: List<Answers>): RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {

    interface ShortOnClickListener {
        fun ShortClick(answer: String, answerId: Long, answerDate: Long)
    }

    var shortOnClickListener: ShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.answerItem.text = answers[position].answer
        Log.d("My Log", "questionId in Adapter: ${answers[position].answerId}")

        holder.answerItem.setOnClickListener {
            shortOnClickListener?.ShortClick(
                answers[position].answer,
                answers[position].answerId,
                answers[position].answerDate
            )
        }
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val answerItem: TextView = itemView.findViewById(R.id.tv_answer_item_content)
    }

}