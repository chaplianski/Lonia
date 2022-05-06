package com.example.lonia.presenter.ui

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lonia.R


class CameraFragment2 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        }

        open fun takePhotoFromCamera() {
            val resultUri: Uri?
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            resultUri = activity!!.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues()
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, resultUri)
            if (intent.resolveActivity(activity!!.packageManager) != null) {
                startActivityForResult(intent, 22)
            }
        }

        fun choosePhotoFromGallary() {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, 11)
        }
/*
        fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == activity.RESULT_OK) {
                if (requestCode == 11) {
                    if (data != null) {
                        val contentURI = data.data
                        try {
                            val bitmap = MediaStore.Images.Media.getBitmap(
                                activity!!.contentResolver,
                                contentURI
                            )
                            //                    String path = saveImage(bitmap);
                            //                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                            iv_froncnic.setImageBitmap(bitmap)
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Toast.makeText(activity, "Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    run { Toast.makeText(activity, "Data not found", Toast.LENGTH_SHORT).show() }
                }
            }
        }

*/




}