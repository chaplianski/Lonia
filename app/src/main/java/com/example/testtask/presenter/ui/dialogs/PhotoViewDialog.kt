package com.example.testtask.presenter.ui.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.testtask.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PhotoViewDialog: BottomSheetDialogFragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(
            R.layout.dialog_photo,
            container, false
        )
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoView = view.findViewById<ImageView>(R.id.iv_dialog_photo_image)
        val photoUri = arguments?.getString("photoUri")

        Glide.with(view.context).load(photoUri)
            //         .error(R.drawable.ic_launcher_background)
            .override(300, 600)
            .centerCrop()
            .into(photoView)



    }


}