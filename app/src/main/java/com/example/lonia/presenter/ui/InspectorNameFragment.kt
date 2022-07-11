package com.example.lonia.presenter.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.lonia.R
import com.google.android.material.textfield.TextInputLayout


//class InspectorNameFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        activity?.title = "Inspector Name"
//        return inflater.inflate(R.layout.fragment_inspector_name, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val navController = Navigation.findNavController(view)
//        val continueButton: Button = view.findViewById(R.id.bt_inspection_name_continue)
//        val inspectorNameField = view.findViewById<TextInputLayout>(R.id.outlinedTextField)
//        var inspectorNameValue = ""
//        val inspectorNameText = view.findViewById<EditText>(R.id.inspector_name_fragment)
//
//        inspectorNameText.doOnTextChanged { inputText, _, _, _ ->
//            if (inputText?.length!! > 0) {
//                inspectorNameField.error = null
//            }
//            inspectorNameValue = inputText.toString()
//        }
//
//        continueButton.setOnClickListener {
//            if (inspectorNameText.text.isBlank()) {
//                inspectorNameField.error = "Your do not enter name"
//            } else {
//                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
//                sharedPref?.edit()?.putString(Constants.CURRENT_INSPECTOR_NAME, inspectorNameValue)
//                    ?.apply()
//                navController.navigate(R.id.action_inspectorNameFragment_to_categoryFragment)
//            }
//        }
//    }
//}