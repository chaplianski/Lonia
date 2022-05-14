package com.example.lonia.presenter.viewmodel

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.graphics.BitmapCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.model.Notes
import com.example.lonia.domain.model.Photos
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


class AnswerViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val updateQuestionUseCase: UpdateQuestionUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val getNotesUseCase: GetNotesUseCase,
) : ViewModel() {


    val _question = MutableLiveData<Questions>()
    val question: LiveData<Questions> get() = _question
    val _notes = MutableLiveData<List<Notes>>()
    val notes: LiveData<List<Notes>> get() = _notes

    val _photos = MutableLiveData<List<Photos>>()
    val photos: LiveData<List<Photos>> get() = _photos


    fun getQuestion(questionId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseQuestion = getQuestionUseCase.execute(questionId)
            _question.postValue(responseQuestion)
        }
    }

    fun updateQuestion(questions: Questions) {
        viewModelScope.launch(Dispatchers.IO) {
            updateQuestionUseCase.execute(questions)
        }
    }

    fun getPhotos(questionId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responsePhotos = getPhotosUseCase.execute(questionId)
            _photos.postValue(responsePhotos)
        }
    }

    fun getNotes(briefcaseId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val notesList = getNotesUseCase.execute(briefcaseId)
            _notes.postValue(notesList)
        }
    }

    fun insertPhoto(uri: Uri, idQuestion: String, contentResolver: ContentResolver) {
        viewModelScope.launch(Dispatchers.IO) {
            var bitmapPhoto = getBitmap(contentResolver, uri)
            val bitmapByteCount: Int? = bitmapPhoto?.let { BitmapCompat.getAllocationByteCount(it) }
            Log.d("MyLog", "answer viewModel bitmap size: ${bitmapByteCount} ")
            Log.d(
                "MyLog",
                "answer viewModel w: ${bitmapPhoto!!.width}, h:  ${bitmapPhoto!!.height}"
            )
            val imageFile = File(uri.toString())
            Log.d("MyLog", "answer viewModel file: ${imageFile} ")
            Log.d("MyLog", "answer viewModel isExist: ${imageFile.exists()} ")

            val currentHeight = bitmapPhoto.height.toDouble()
            val currentWidth = bitmapPhoto.width.toDouble()
            Log.d(
                "MyLog",
                "answer viewModel bitmap size currentWidth: ${currentWidth} currentHeight: $currentHeight"
            )

            if (bitmapPhoto.height > bitmapPhoto.width && bitmapPhoto.width > 600) {
                val cameraCoef = currentHeight / currentWidth
                val height = 600 * cameraCoef
                bitmapPhoto = getResizedBitmap(bitmapPhoto, 600.0, height)
                Log.d(
                    "MyLog",
                    "answer viewModel bitmapNew vertical w: ${bitmapPhoto!!.width}, h:  ${bitmapPhoto!!.height}"
                )
                val bitmapByteCount: Int? =
                    bitmapPhoto?.let { BitmapCompat.getAllocationByteCount(it) }
                Log.d("MyLog", "answer viewModel bitmap size: ${bitmapByteCount} ")
            }

            if (currentHeight < currentWidth && currentHeight > 600) {
                val cameraCoef = (currentWidth / currentHeight)
                Log.d("MyLog", "answer viewModel bitmap cameraCoef: ${cameraCoef} ")
                val width = 600 * cameraCoef

                Log.d("MyLog", "answer viewModel bitmap size width: ${width} ")
                bitmapPhoto = getResizedBitmap(bitmapPhoto, width, 600.0)
                Log.d(
                    "MyLog",
                    "answer viewModel bitmapNew horizontal w: ${bitmapPhoto!!.width}, h:  ${bitmapPhoto!!.height}"
                )
                val bitmapByteCount: Int? =
                    bitmapPhoto?.let { BitmapCompat.getAllocationByteCount(it) }
                Log.d("MyLog", "answer viewModel bitmap size: ${bitmapByteCount} ")
            }

            val photo = bitmapPhoto.let {
                Photos(
                    photoId = 0,
                    questionId = idQuestion,
                    photoUri = it
                )
            }

            insertPhotoUseCase.execute(photo)
            getPhotos(idQuestion)
        }
    }

    fun getBitmap(contentResolver: ContentResolver, fileUri: Uri?): Bitmap? {

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, fileUri!!))
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getResizedBitmap(bm: Bitmap, newWidth: Double, newHeight: Double): Bitmap? {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
        )
        bm.recycle()
        return resizedBitmap
    }

    fun deletePhoto(photo: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePhotoUseCase.execute(photo)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}