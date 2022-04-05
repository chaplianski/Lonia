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
import com.example.testtask.presenter.factories.AnswersViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswersViewModel


class AnswersFragment : Fragment() {

    lateinit var answersViewModelFactory: AnswersViewModelFactory
    val answersViewModel: AnswersViewModel by viewModels {answersViewModelFactory}

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .answersFragmentInject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answers, container, false)
    }


}