package com.example.testtask.presenter.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.testtask.di.DaggerAppComponent
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .datePickerFragmentInject(this)
        super.onAttach(context)

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(context!!, this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        private val DATA_DIALOG_TAG = DatePickerFragment::class.java.simpleName
        @JvmStatic
        private val KEY_RESPONSE = "Key"
        @JvmStatic
        private val REQUEST_KEY = "$DATA_DIALOG_TAG: defaultRequestKey"
        @JvmStatic
        private val DATA_ANSWER = "Data_answer"

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