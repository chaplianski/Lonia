package com.example.testtask.presenter.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.room.Insert
import com.example.testtask.R
import com.example.testtask.databinding.FragmentAnswerBinding
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.presenter.factories.AnswerViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AnswerFragment : Fragment() {

    @Inject
    lateinit var answerViewModelFactory: AnswerViewModelFactory
    val answerViewModel: AnswerViewModel by viewModels { answerViewModelFactory }

    var _binding: FragmentAnswerBinding? = null
    val binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .answerFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Answer"
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationButton = binding.tvAnswerInformation
        val dateAnswer = binding.btAnswerDate
        val answerTextVew = binding.etAnswerContent
        val positiveButton = binding.btAnswerYes
        val negativeButton = binding.btAnswerNo
        val questionTextView = binding.tvAnswerTitle
        val photoTextView = binding.tvAnswerAttachPhoto
        val navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val question = sharedPref?.getString(Constants.CURRENT_QUESTION, "").toString()
        val questionId = sharedPref?.getString(Constants.CURRENR_QUESTION_ID, "").toString()
        val answerId = sharedPref?.getInt(Constants.CURRENT_ANSWER_ID, 0)
        questionTextView.text = question

        if (answerId != null) {
            if (!answerId.equals(0)) {
                answerViewModel.getAnswer(answerId.toLong())
            }
        }
        val formateDate = SimpleDateFormat("dd.MM.yyyy", Locale.US)

        informationButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_informationFragment)
        }

        var answerDate: Long = 0

        dateAnswer.setOnClickListener {

            val getData = Calendar.getInstance()
            val dataPicker = DatePickerDialog(
                context!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { datePicker, i, i2, i3 ->
                    val selectData = Calendar.getInstance()
                    selectData.set(Calendar.YEAR, i)
                    selectData.set(Calendar.MONTH, i2)
                    selectData.set(Calendar.DAY_OF_MONTH, i3)
                    val date = formateDate.format(selectData.timeInMillis)
                    dateAnswer.text = date
                    answerDate = selectData.timeInMillis
                },
                getData.get(Calendar.YEAR),
                getData.get(Calendar.MONTH),
                getData.get(Calendar.DAY_OF_MONTH)
            )
            dataPicker.show()
        }


        var currentAnswerId = 0L
        answerViewModel.answer.observe(this.viewLifecycleOwner, { answer ->

            answerTextVew.setText(answer.answer)
            if (!answer.answerDate.equals(0) && !answer.answerDate.equals(null)) {
                val data = answer.answerDate
                currentAnswerId = answer.answerId
                dateAnswer.text = formateDate.format(data)
            }
        })

        positiveButton.setOnClickListener {
            Log.d("My Log", "In MS: ${answerDate}")
            val answerValue = answerTextVew.text.toString()
            val questions = Questions(
                questionid = questionId,
                comment = "",
                dateOfInspection = "",
                answer = 0,
                questioncode = "",
                question = "",
                commentForQuestion = "",
                categoryid = "",
                origin = "",
                categorynewid = "",
                isAnswered = true,
                images = 0,
                briefCaseId = 0
            )
            val answer = Answers(
                answer = answerValue,
                answerDate = answerDate,
                answerId = currentAnswerId
            )

            if (currentAnswerId != 0L) {
                answerViewModel.updateAnswer(answer)
            } else {
                answerViewModel.updateQuestionAndAddAnswer(questions, answer)
            }

            navController.navigate(R.id.briefCaseFragment)
        }

        negativeButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_questionsFragment)
        }
    }
}

