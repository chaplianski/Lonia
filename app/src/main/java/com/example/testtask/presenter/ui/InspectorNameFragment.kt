package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.testtask.R


class InspectorNameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Inspector Name"
        return inflater.inflate(R.layout.fragment_inspector_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val continueButton: Button = view.findViewById(R.id.bt_inspection_name_continue)
        val inspectorNameTextView = view.findViewById<EditText>(R.id.et_inspection_name)

        continueButton.setOnClickListener {
            val inspectorName = inspectorNameTextView.text.toString()
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit()?.putString(Constants.CURRENT_INSPECTOR_NAME, inspectorName)?.apply()

            navController.navigate(R.id.action_inspectorNameFragment_to_categoryFragment)
        }
    }
}