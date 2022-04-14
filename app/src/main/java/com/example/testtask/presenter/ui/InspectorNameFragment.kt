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
import android.widget.Toast
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
        val inspectorNameField = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.outlinedTextField)
        val inspectorNameValue = inspectorNameField.editText?.text.toString()
        val inspectorNameText = view.findViewById<EditText>(R.id.inspector_name_fragment)


        continueButton.setOnClickListener {
            if (inspectorNameText.text.isBlank()){
                inspectorNameField.error = "Your do not enter name"
                Toast.makeText(context, "Please, enter inspector name", Toast.LENGTH_SHORT).show()

            } else {
                val inspectorName = inspectorNameValue
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                sharedPref?.edit()?.putString(Constants.CURRENT_INSPECTOR_NAME, inspectorName)?.apply()

                navController.navigate(R.id.action_inspectorNameFragment_to_categoryFragment)
            }
        }
    }
}