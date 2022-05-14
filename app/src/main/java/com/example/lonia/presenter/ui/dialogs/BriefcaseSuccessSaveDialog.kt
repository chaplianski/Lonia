package com.example.lonia.presenter.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class BriefcaseSuccessSaveDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(
                SUCCESS_REQUEST_KEY, bundleOf(
                    SUCCESS_KEY_RESPONSE to which,
 //                   BriefcaseAnswerDialog.ANSWER_BRIEFCASE_ID to briefcaseId
                )
            )
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("Excellent!")
            .setMessage("Data saved successfully")
            .setPositiveButton("Go to list briefcases", listener)
            .create()
        return dialog
    }

    companion object {
        val SUCCESS_REQUEST_KEY = "success request key"
        val SUCCESS_KEY_RESPONSE = "success key response"
    }
}