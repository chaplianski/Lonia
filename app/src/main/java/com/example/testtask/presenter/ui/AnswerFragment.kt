package com.example.testtask.presenter.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.databinding.FragmentAnswerBinding
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Answers
import com.example.testtask.presenter.factories.AnswerViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswerViewModel


class AnswerFragment : Fragment() {

    lateinit var answerViewModelFactory: AnswerViewModelFactory
    val answerViewModel: AnswerViewModel by viewModels {answerViewModelFactory}

    var _binding: FragmentAnswerBinding?  = null
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
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationButton = binding.tvAnswerInformation
        val dateTextView = binding.etAnswerDate
        val answerTextVew = binding.etAnswerContent
        val yesButton = binding.btAnswerYes
        val noButton = binding.btAnswerNo
        val questionTextView = binding.tvAnswerTitle
        val photoTextView = binding.tvAnswerAttachPhoto
        val navController = Navigation.findNavController(view)


        informationButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_informationFragment)
        }

        yesButton.setOnClickListener {
            val answerDate = dateTextView.text.toString().toLong()
            val answerValue = answerTextVew.text.toString()
            val answer = Answers(
                answerValue = answerValue,
                answerDate = answerDate,
                answerId = 0
            )

            answerViewModel.addAnswer(answer)
            navController.navigate(R.id.action_answersFragment_to_briefCaseFragment)
        }

        noButton.setOnClickListener {

        }

        dateTextView.setOnClickListener {
            showDatePickerDialog(view)
        }



    }

    fun setupDialogFragmentListener(){
        parentFragmentManager.setFragmentResultListener("Tag,", this, FragmentResultListener{_,result ->
            val dateAnswer = result.getInt("",)
            when(dateAnswer){
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(context, "asdfsd", Toast.LENGTH_SHORT)
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(context, "asdfsd", Toast.LENGTH_SHORT)
            }

        })
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(parentFragmentManager, "Tag"   )
    }

}