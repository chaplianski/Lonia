package com.example.testtask.presenter.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.io.File
import java.util.jar.Manifest

class CameraFragment : Fragment() {
/*

    var fotoapparat: Fotoapparat? = null
    val filename = "test.png"
    val sd = Environment.getExternalStorageDirectory()
    val dest = File(sd, filename)
    var fotoapparatState: FotoapparatState? = null
    var cameraStatus: CameraState? = null
    var flashState: FlashState? = null



    val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .cameraFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabFlash = view.findViewById<FloatingActionButton>(R.id.fab_flash)
        val fabCamera = view.findViewById<FloatingActionButton>(R.id.fab_camera)
        val fabSwitchCamera = view.findViewById<FloatingActionButton>(R.id.fab_switch_camera)

        createFotoapparat()


        cameraStatus = CameraState.BACK
        flashState = FlashState.OFF
        fotoapparatState = FotoapparatState.OFF

        fabCamera.setOnClickListener {
            Log.d("My Log", "camera fragment: take photo")
            takePhoto()
        }

        fabSwitchCamera.setOnClickListener {
            Log.d("My Log", "camera fragment: switch")
            switchCamera()
        }

        fabFlash.setOnClickListener {
            Log.d("My Log", "camera fragment: flash state")
            changeFlashState()
        }
    }

    private fun createFotoapparat() {
        val cameraView = view?.findViewById<CameraView>(R.id.camera_view)

        fotoapparat = cameraView?.let {
            context?.let { it1 ->
                Fotoapparat(
                    context = it1,
                    view = it,
                    scaleType = ImageView.ScaleType.CenterCrop,
                    lensPosition = back(),
                    logger = loggers(
                        logcat()
                    ),
                    cameraErrorCallback = { error ->
                        println("Recorder errors: $error")
                    }
                )
            }
        }


    }

    private fun changeFlashState() {
        fotoapparat?.updateConfiguration(
            CameraConfiguration(
                flashMode = if (flashState == FlashState.TORCH) off() else torch()
            )
        )
        Log.d("My Log", "camera fragment: flash state - $flashState")
        if (flashState == FlashState.TORCH) flashState = FlashState.OFF
        else flashState = FlashState.TORCH
    }

    private fun switchCamera() {
        fotoapparat?.switchTo(
            lensPosition = if (cameraStatus == CameraState.BACK) front() else back(),
            cameraConfiguration = CameraConfiguration()
        )

        if (cameraStatus == CameraState.BACK) cameraStatus = CameraState.FRONT
        else cameraStatus = CameraState.BACK
    }

    private fun takePhoto() {
        if (hasNoPermissions()) {
            requestPermission()
        } else {
            fotoapparat
                ?.takePicture()
                ?.saveToFile(dest)
        }
    }

    override fun onStart() {
        super.onStart()
        if (hasNoPermissions()) {
            requestPermission()
        } else {
            fotoapparat?.start()
            fotoapparatState = FotoapparatState.ON
        }
    }

    private fun hasNoPermissions(): Boolean {

        return ContextCompat.checkSelfPermission(
            context!!,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            context!!,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            context!!,
            android.Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        activity?.let { ActivityCompat.requestPermissions(it, permissions, 0) }
    }

    override fun onStop() {
        super.onStop()
        fotoapparat?.stop()
        FotoapparatState.OFF
        val navController = view?.let { Navigation.findNavController(it) }
        navController?.navigate(com.example.testtask.R.id.action_cameraFragment_to_answerFragment)
    }

    override fun onResume() {
        super.onResume()
        if (!hasNoPermissions() && fotoapparatState == FotoapparatState.OFF) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        //    val navController = view?.let { Navigation.findNavController(it) }
        //    navController?.navigate(com.example.testtask.R.id.action_cameraFragment_to_answerFragment)
            //    activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()

        }
    }
*/
}

enum class CameraState {
    FRONT, BACK
}

enum class FlashState {
    TORCH, OFF
}

enum class FotoapparatState {
    ON, OFF
}
