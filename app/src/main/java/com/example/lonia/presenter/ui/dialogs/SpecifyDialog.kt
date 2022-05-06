package com.example.lonia.presenter.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner


class SpecifyDialog : DialogFragment() {

    private val itemName: String? get() = requireArguments().getString(NAME_ITEM)
    private val itemValue: String? get() = requireArguments().getString(VALUE_ITEM)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = AlertDialog.Builder(context)
            .setTitle("Are your sure?")
            .setCancelable(true)
            .setMessage("You check $itemName $itemValue")
            .setPositiveButton("Continue"){_, _ ->
                val currentItem = itemValue
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to currentItem))
            }
            .setNegativeButton("Cancel", null)
            .create()
        return dialog
    }

    companion object {

        val NAME_ITEM = "name item"
        val VALUE_ITEM = "value item"
        val KEY_RESPONSE = "key response"

        val TAG = SpecifyDialog::class.java.simpleName
        val REQUEST_KEY = "$TAG: default request key"

        fun show(manager: FragmentManager, spesifyNameItem: String, specifyValue: String) {
            val dialogFragment = SpecifyDialog()
            dialogFragment.arguments = bundleOf(
                NAME_ITEM to spesifyNameItem,
                VALUE_ITEM to specifyValue
            )
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (String) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener { key, result ->
                result.getString(KEY_RESPONSE)?.let { listener.invoke(it) }

            })
        }



    }
}