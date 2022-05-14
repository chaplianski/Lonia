package com.example.lonia.presenter.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lonia.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class InformationFragment : BottomSheetDialogFragment() {


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(
            R.layout.dialog_comment,
            container, false
        )
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvInformation: TextView = view.findViewById(R.id.tv_information)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        tvInformation.text = sharedPref?.getString(Constants.CURRENT_COMMENT, "")


    }


}