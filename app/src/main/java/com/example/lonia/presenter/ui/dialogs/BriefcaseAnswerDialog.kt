package com.example.lonia.presenter.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class BriefcaseAnswerDialog : DialogFragment() {

    private val briefcaseId: Long? get() = requireArguments().getLong(ANSWER_BRIEFCASE_ID)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(
                ANSWER_REQUEST_KEY, bundleOf(
                    ANSWER_KEY_RESPONSE to which,
                    ANSWER_BRIEFCASE_ID to briefcaseId
                )
            )
        }

        val answerDialogItems = arrayOf(
            "Sent data and save on device",
            "Sent data and delete from device",
            "Look answers"
        )
        val dialog = AlertDialog.Builder(context)
            .setTitle("Choose variant of work with data")
            .setItems(answerDialogItems, listener)
            .create()
        return dialog
    }

    companion object {
        val ANSWER_REQUEST_KEY = "answer request key"
        val ANSWER_KEY_RESPONSE = "answer key response"
        val ANSWER_BRIEFCASE_ID = "answer briefcase id"
    }
}