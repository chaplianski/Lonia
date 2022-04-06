package com.example.testtask.presenter.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.room.Insert
import com.example.testtask.R
import com.example.testtask.databinding.FragmentAnswerBinding
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.presenter.factories.AnswerViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AnswerFragment : Fragment() {

    @Inject
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
        val positiveButton = binding.btAnswerYes
        val negativeButton = binding.btAnswerNo
        val questionTextView = binding.tvAnswerTitle
        val photoTextView = binding.tvAnswerAttachPhoto
        val navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val question = sharedPref?.getString(Constants.CURRENT_QUESTION, "").toString()
        val questionId = sharedPref?.getString(Constants.CURRENR_QUESTION_ID, "").toString()
        Log.d("My Log", "questionId in AnswerFrsgment: $questionId")
        questionTextView.text = question

        val datePicker = view?.findViewById<DatePicker>(R.id.date_Picker)


        informationButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_informationFragment)
        }



        dateTextView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())


        val  dataChangrListener: DatePicker.OnDateChangedListener = object : DatePicker.OnDateChangedListener{
            override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                dateTextView.text = "$p3.$p2.$p1"
            }
        }


        val datapicker = view.findViewById<DatePicker>(R.id.date_Picker)
        datapicker.setOnDateChangedListener (dataChangrListener)

        positiveButton.setOnClickListener {

        //    val answerDate =  //dateTextView.text.toString().toLong()
            val answerValue = answerTextVew.text.toString()
            val questions = Questions (
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
                answerDate = 4234234124312L,
                answerId = 0
            )
            Log.d("My Log", "questionId in AnswerFragment: $questionId")
            answerViewModel.updateQuestionAndAddAnswer(questions, answer)
            navController.navigate(R.id.briefCaseFragment)
        }

        negativeButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_questionsFragment)
        }









     //   val textView: TextView  = findViewById(R.id.textView_date)



/*
        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            dateTextView.text = sdf.format(cal.time)

        }

        dateTextView.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(
                    it1, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        } */
    }



 }

    private fun updateDateInView(dataTextView: TextView, cal: Calendar) {
        val myFormat = "MM.dd.yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dataTextView.text = sdf.format(cal.getTime())
    }

/*

    fun setupDialogFragmentListener(){
        parentFragmentManager.setFragmentResultListener(DatePickerFragment.DATA_DIALOG_TAG, this, FragmentResultListener{_,result ->
            val dateAnswer = result.getInt(DatePickerFragment.KEY_RESPONSE)

            when(dateAnswer){
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(context, "asdfsd", Toast.LENGTH_SHORT)
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(context, "asdfsd", Toast.LENGTH_SHORT)
            }

        })


    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        val dataAnswer = 2323421342L
        val bundle = Bundle()
        bundle.putLong("Current date", dataAnswer)

        newFragment.show(parentFragmentManager, DatePickerFragment.DATA_DIALOG_TAG)
    }



}*/