package com.example.testtask.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R

class AnswersAdapter (private val answers: List<String>): RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.answerItem.text = answers[position]
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val answerItem: TextView = itemView.findViewById(R.id.tv_answer_item_content)
    }

}