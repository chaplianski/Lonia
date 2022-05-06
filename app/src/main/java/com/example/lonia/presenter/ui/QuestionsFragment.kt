package com.example.lonia.presenter.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.lonia.R
import com.example.lonia.presenter.adapter.QuestionsExpanbleAdapter
import com.example.lonia.presenter.factories.QuestionsViewModelFactory
import com.example.lonia.presenter.viewmodel.QuestionsViewModel
import com.example.lonia.di.DaggerAppComponent
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Briefcases"

        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)

        val checkItemsButton: FloatingActionButton = view.findViewById(R.id.bt_questions_check)
        val answerItemsButton: FloatingActionButton = view.findViewById(R.id.bt_questions_answer)
        var isCheck = false


        if (briefcaseId != null) {
            questionViewModel.getQuestionList(briefcaseId, isCheck)
        }

        questionViewModel.questionList.observe(this.viewLifecycleOwner, {

            val expandableListView = view.findViewById<ExpandableListView>(R.id.elv_questions_expandable_list)
            val questionsAdapter = it
            expandableListView.setAdapter(questionsAdapter)

            checkItemsButton.setOnClickListener {

                isCheck = !isCheck

                if (briefcaseId != null) {
                    questionViewModel.getQuestionList(briefcaseId, isCheck)
                }
            }




            val questionsIdList = mutableListOf<String>()

     //       val questionsAdapter = QuestionsAdapter(it)
     //       val questionsRV = view.findViewById<RecyclerView>(R.id.rv_questions)
     //       questionsRV.layoutManager = LinearLayoutManager(context)
     //       questionsRV.adapter = questionsAdapter

            questionsAdapter.shortOnClickListener =
                object : QuestionsExpanbleAdapter.ShortOnClickListener {
                    override fun ShortClick(
                        question: String,
                        comment: String,
                        questionId: String,
                        isAnswered: Boolean
                    ) {
                        if (isCheck){
                            if(questionsIdList.contains(questionId)){
                                for ((index, element) in questionsIdList.withIndex()){
                                    if (element == questionId) {
                                        questionsIdList.removeAt(index)
                                        break
                                    }
                                }
                            }else{
                                questionsIdList.add(questionId)
                            }

                            answerItemsButton.setOnClickListener {
                                val arrayQuestions = questionsIdList.toTypedArray()
                                val bundle = Bundle()
                                bundle.putStringArray(Constants.LIST_QUESTIONS_ID, arrayQuestions)
                                sharedPref?.edit()?.putBoolean(Constants.CURRENT_ISANSWERED, false)?.apply()
                                val navController = Navigation.findNavController(view)
                                navController.navigate(R.id.action_questionsNotesFragment_to_answerFragment, bundle)

                            }

                        } else {

                            sharedPref?.edit()?.putString(Constants.CURRENT_QUESTION_ID, questionId)                                ?.apply()
                            sharedPref?.edit()?.putString(Constants.CURRENT_QUESTION, question)?.apply()
                            sharedPref?.edit()?.putString(Constants.CURRENT_COMMENT, comment)?.apply()
                            sharedPref?.edit()?.putBoolean(Constants.CURRENT_ISANSWERED, isAnswered)?.apply()
                            Log.d("My Log","from question fragment: isAnswered: $isAnswered")
                            val navController = Navigation.findNavController(view)
                            navController.navigate(R.id.action_questionsNotesFragment_to_answerFragment)

                        }


                    }
                }
        })
    }



    companion object{

        fun getInstance() = QuestionsFragment()
    }
}