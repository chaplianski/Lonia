package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class QuestionAdapter (private  val questions: List<String>): RecyclerView.Adapter <QuestionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionItem.text = questions[position]
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
            val questionItem: TextView = itemView.findViewById(R.id.tv_question_item_content)
    }
}