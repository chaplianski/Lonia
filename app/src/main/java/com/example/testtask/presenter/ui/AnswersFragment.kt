package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.AnswersAdapter
import com.example.testtask.presenter.factories.AnswersViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswersViewModel
import javax.inject.Inject


class AnswersFragment : Fragment() {

    @Inject
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)

        if (briefcaseId != null) {
            answersViewModel.getAnswers(briefcaseId)
        }

        answersViewModel.answersList.observe(this.viewLifecycleOwner,{

            val answersAdapter = AnswersAdapter(it)
            val answersRV = view.findViewById<RecyclerView>(R.id.rv_answers)
            answersRV.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            answersRV.adapter = answersAdapter

            answersAdapter.shortOnClickListener =
                object : AnswersAdapter.ShortOnClickListener{
                    override fun ShortClick(answer: String, answerId: Long, answerDate: Long) {

                        navController.navigate(R.id.action_questionsFragment_to_answerFragment)
                    }

                }

        })


    }



}