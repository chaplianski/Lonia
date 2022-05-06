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
        /*       dialog?.setOnShowListener { dialog ->
                   val layout: FrameLayout? =
                       (dialog as BottomSheetDialog).findViewById(com.google.android.material.R.id.design_bottom_sheet)
                   layout?.let {
                       val behavior = BottomSheetBehavior.from(it)
                       behavior.state = BottomSheetBehavior.STATE_EXPANDED
                       behavior.skipCollapsed = true
                   }
               }

       */
//     val bottomSheetBehavior: BottomSheetBehavior<View> = BottomSheetBehavior.from(sheetBottom)
        //     bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        val v = inflater.inflate(
            R.layout.dialog_comment,
            container, false
        )
        //     val bottomSheet = v
        //          .findViewById<ConstraintLayout>(R.id.design_bottom_sheet)
        //    val behavior: BottomSheetBehavior<*>?
        //     if (bottomSheet != null) {
        //         behavior = BottomSheetBehavior.from(bottomSheet)
        //        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        //         behavior?.isDraggable = false
        //     }

        //  if (v.findViewById(R.layout.dialog_comment) != null ){
        //        val sheetBottom = view?.findViewById<ConstraintLayout>(R.layout.dialog_comment)
        //        val bottomSheetBehavior: BottomSheetBehavior<*>?
        //         bottomSheetBehavior = BottomSheetBehavior.from(sheetBottom)
        //        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        //     }


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvInformation: TextView = view.findViewById(R.id.tv_information)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        tvInformation.text = sharedPref?.getString(Constants.CURRENT_COMMENT, "")


    }


}