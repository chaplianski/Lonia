package com.example.lonia.presenter.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment


class QuestionnariesDialogFragment: DialogFragment() {

    private val title: String? get() = requireArguments().getString(TITLE_KEY_RESPONSE)
    private val itemValue: String? get() = requireArguments().getString(QUESTIONNARIES_VALUE_ITEM)
    private val qid: Int? get() = requireArguments().getInt(QID_KEY_RESPONSE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener {_, which ->
            parentFragmentManager.setFragmentResult(QUESTIONNARIES_REQUEST_KEY, bundleOf(
                QUESTIONNARIES_KEY_RESPONSE to which,
                TITLE_KEY_RESPONSE to title,
                QID_KEY_RESPONSE to qid
            ))

        }
        val dialog = AlertDialog.Builder(context)
            .setTitle("Are your sure?")
            .setCancelable(true)
            .setMessage("You check $title $itemValue")
            .setPositiveButton("Continue", listener)
            .setNegativeButton("Cancel", null)
            .create()
        return dialog

    }

    companion object {
        val QUESTIONNARIES_REQUEST_KEY = "questionnaries request key"
        val TITLE_KEY_RESPONSE = "title key response"
        val QID_KEY_RESPONSE = "qid key response"
        val QUESTIONNARIES_VALUE_ITEM = "questionnaries item value"
        val QUESTIONNARIES_KEY_RESPONSE = "questionnaries key response"
    }
}