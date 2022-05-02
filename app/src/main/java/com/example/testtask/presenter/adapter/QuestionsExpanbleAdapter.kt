package com.example.testtask.presenter.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.testtask.R
import com.example.testtask.domain.model.Questions
import kotlin.reflect.jvm.internal.impl.util.Check

class QuestionsExpanbleAdapter(
    private val listGroup: List<String>,
    private val listChild: Map<String, List<Questions>>,
    private val isCheck: Boolean
) : BaseExpandableListAdapter() {

    interface ShortOnClickListener {
        fun ShortClick(question: String, comment: String, questionId: String, answer: Int)
    }

    var shortOnClickListener: ShortOnClickListener? = null


    override fun getGroupCount(): Int {
        return listGroup.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return this.listChild[this.listGroup[p0]]!!.size

    }

    override fun getGroup(p0: Int): Any {
        return listGroup[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return this.listChild[this.listGroup[p0]]!![p1]

    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {

        val categoryTitle = getGroup(p0) as String

        val v = LayoutInflater.from(p3?.context).inflate(R.layout.category_question_item, p3, false)
        val questionTextView1 = v.findViewById<TextView>(R.id.tv_category_questions_title)
        questionTextView1.setText(categoryTitle)

    return v


    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {


        val question = getChild(p0, p1) as Questions
        val questionTitle = question.question



        val v = LayoutInflater.from(p4?.context).inflate(R.layout.question_item, p4, false)
        if (isCheck){
            v.findViewById<CheckBox>(R.id.cb_question_item_checkBox).visibility = View.VISIBLE

        }
        val questionTextView1 = v.findViewById<TextView>(R.id.tv_question_item_content)
        val questionCheckBox = v.findViewById<CheckBox>(R.id.cb_question_item_checkBox)
        questionTextView1.setText(questionTitle)

        questionTextView1.setOnClickListener {
            questionCheckBox.isChecked = !questionCheckBox.isChecked

            shortOnClickListener?.ShortClick(
                question.question,
                question.comment,
                question.questionid,
                question.answer
            )
        }

        questionCheckBox.setOnClickListener {

            shortOnClickListener?.ShortClick(
                question.question,
                question.comment,
                question.questionid,
                question.answer
            )
        }

       return v

    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}