package com.example.lonia.presenter.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CommentFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)


    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    companion object{

        val COMMENT_TAG = "comment tag"
        val REQUEST_KEY = "request key"
    }
}
