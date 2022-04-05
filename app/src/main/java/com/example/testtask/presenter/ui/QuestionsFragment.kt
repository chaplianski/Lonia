package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.factories.QuestionnairesViewModelFactory
import com.example.testtask.presenter.viewmodel.QuestionsViewModel


class QuestionsFragment : Fragment() {

    lateinit var questionsViewModelFactory: QuestionnairesViewModelFactory
    val questionViewModel: QuestionsViewModel by viewModels {questionsViewModelFactory}

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .questionsFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }




}