package com.example.lonia.presenter.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment

class BriefcaseErrorSaveDialog : DialogFragment() {

    private val messageError: String? get() = requireArguments().getString(MESSAGE_ERROR)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(
                ERROR_REQUEST_KEY, bundleOf(
                    ERROR_KEY_RESPONSE to which
                )
            )
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("$messageError")
            .setMessage("Data not saved")
            .setPositiveButton("Try again", listener)
            .setNegativeButton("Cancel", null)
            .create()
        return dialog
    }

    companion object {
        val MESSAGE_ERROR = "message error"
        val ERROR_REQUEST_KEY = "error request key"
        val ERROR_KEY_RESPONSE = "error key response"
    }
}