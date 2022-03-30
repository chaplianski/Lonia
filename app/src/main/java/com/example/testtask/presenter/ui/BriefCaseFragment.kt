package com.example.testtask.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.testtask.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BriefCaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brief_case, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val addNewBriefCaseButton: FloatingActionButton = view.findViewById(R.id.bt_briefcase_add)

        addNewBriefCaseButton.setOnClickListener {

            navController.navigate(R.id.vesselsFragment)
        }
    }


}