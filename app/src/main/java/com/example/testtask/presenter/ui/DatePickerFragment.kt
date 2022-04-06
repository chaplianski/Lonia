package com.example.testtask.presenter.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import java.time.Year
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var year = Calendar.YEAR
    var month = Calendar.MONTH
    var day = Calendar.DAY_OF_MONTH

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .datePickerFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val  listener = DialogInterface.OnClickListener{_, buttonClick ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to buttonClick))

        }
        val c = Calendar.getInstance()
        val currentData = arguments?.getLong("Current data")


        if (currentData != null){
            c.setTimeInMillis(currentData)
            day = c.get(Calendar.DAY_OF_MONTH)
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)

        }else {
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }
        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(context!!, this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

        val dateAnswer: Calendar = Calendar.getInstance()
            dateAnswer.set(Calendar.YEAR, p1, Calendar.MONTH, p2, Calendar.DAY_OF_MONTH, p3)
        val dataAnswerLong = dateAnswer.timeInMillis
//        val dataAnswerText: TextView = view!!.findViewById(R.id.et_answer_date)
 //       dataAnswerText.text = "$day.$month.$year"

    }




    companion object {

        val DATA_DIALOG_TAG = DatePickerFragment::class.java.simpleName
        val REQUEST_KEY = "$DATA_DIALOG_TAG: defaultRequestKey"
        val KEY_RESPONSE = "Response"
        val DATA_ANSWER = "Data_answer"

        fun show(manager: FragmentManager, dateAnswer: Long) {
            val dialogFragment = DatePickerFragment()
            dialogFragment.arguments = bundleOf(DATA_ANSWER to dateAnswer)
            dialogFragment.show(manager, DATA_DIALOG_TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Int) -> Unit
        ) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener{_, result ->
                listener.invoke(result.getInt(KEY_RESPONSE))
            })

        }

    }
}