package com.example.lonia.presenter.ui

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.lonia.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import javax.inject.Inject

class PhotoPicker @Inject constructor(
    val context: Context,
    activityResultRegistry: ActivityResultRegistry,
    private val callback: (image: Uri?) -> Unit
) {

    private lateinit var photoUri: Uri

    //Запрашиваем картинку на устройстве
    private val getContentLauncher = activityResultRegistry.register(
        REGISTRY_KEY_GET_CONTENT,
        ActivityResultContracts.GetContent()
    ) { uri ->

   //     Log.d ("My Log", "Photo picker uri: $uri")
  //     Log.d ("My Log", "Photo picker uriPath: ${uri.path}")
   //  val newUri = getTmpFileUri()
        callback.invoke(uri)

    // val galleryUri = Uri.parse(uri.path.toString())
     //   Log.d ("My Log", "Photo picker newUri: $newUri, galleryUri: $galleryUri")

     //   val file = getFile(context, uri)


      // file?.copyTo(File(getTmpFileUri().path.toString()), false)

    }


    //Вызываем камеру
    private val takePhotoLauncher = activityResultRegistry.register(
        REGISTRY_KEY_TAKE_PHOTO,
        ActivityResultContracts.TakePicture()
    ) { result ->
        if (result && this::photoUri.isInitialized) callback.invoke(photoUri)
    }

    private val requestPermissionLauncher = activityResultRegistry.register(
        REGISTRY_KEY_PERMISSION,
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            photoUri = getTmpFileUri()
            takePhotoLauncher.launch(photoUri)
        }
    }

    fun pickPhoto() { getContentLauncher.launch("image/*") }

    fun takePhoto() { requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)}

    private fun getTmpFileUri(): Uri {

        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    //    val tmpFile = File.createTempFile("br", ".jpg", storageDir)
        val tmpFile = File(context.cacheDir, "temp.jpg")
        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }

    companion object {
        val REGISTRY_KEY_PERMISSION = "registry key permissin"
        val REGISTRY_KEY_TAKE_PHOTO = "registry key take photo"
        val REGISTRY_KEY_GET_CONTENT = "registry key get content"
    }

    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File? {
        val destinationFilename =
            File(context.filesDir.path + File.separatorChar + queryName(context, uri))
        try {
            context.contentResolver.openInputStream(uri).use { ins ->
                createFileFromStream(
                    ins!!,
                    destinationFilename
                )
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
        return destinationFilename
    }

    fun createFileFromStream(ins: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while (ins.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message!!)
            ex.printStackTrace()
        }
    }

    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)!!
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }

}