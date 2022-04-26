package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.QuestionsAdapter
import com.example.testtask.presenter.factories.QuestionsViewModelFactory
import com.example.testtask.presenter.viewmodel.QuestionsViewModel
import javax.inject.Inject


class QuestionsFragment : Fragment() {

    @Inject
    lateinit var questionsViewModelFactory: QuestionsViewModelFactory
    val questionViewModel: QuestionsViewModel by viewModels { questionsViewModelFactory }

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
        activity?.title = "Questions"
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)
        val navController = Navigation.findNavController(view)

        if (briefcaseId != null) {
            questionViewModel.getQuestionList(briefcaseId)
        }

        questionViewModel.questionList.observe(this.viewLifecycleOwner, {

            val questionsAdapter = QuestionsAdapter(it)
            val questionsRV = view.findViewById<RecyclerView>(R.id.rv_questions)
            questionsRV.layoutManager = LinearLayoutManager(context)
            questionsRV.adapter = questionsAdapter

            questionsAdapter.shortOnClickListener =
                object : QuestionsAdapter.ShortOnClickListener {
                    override fun ShortClick(
                        question: String,
                        comment: String,
                        questionId: String,
                        answer: Int
                    ) {
                        sharedPref?.edit()?.putString(Constants.CURRENR_QUESTION_ID, questionId)
                            ?.apply()
                        sharedPref?.edit()?.putString(Constants.CURRENT_QUESTION, question)?.apply()
                        Log.d("My Log", "Questions Fragment question: $question")
                        sharedPref?.edit()?.putString(Constants.CURRENT_COMMENT, comment)?.apply()
                        sharedPref?.edit()?.putInt(Constants.CURRENT_ANSWER_ID, answer)
                            ?.apply()
                        navController.navigate(R.id.action_questionsFragment_to_answerFragment)
                    }
                }
        })
    }
}