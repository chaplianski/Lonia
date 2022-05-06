package com.example.lonia.presenter.viewmodel

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lonia.domain.model.Answers
import com.example.lonia.domain.model.Notes
import com.example.lonia.domain.model.Photos
import com.example.lonia.domain.model.Questions
import com.example.lonia.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import kotlin.math.round


class AnswerViewModel @Inject constructor(
    private val getAnswerUseCase: GetAnswerUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
    private val updateAnswerUseCase: UpdateAnswerUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val updatePhotosUseCase: UpdatePhotosUseCase,
    private val insertPhotoUseCase: InsertPhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val updateListQuestionsUseCase: UpdateListQuestionsUseCase,
    private val updateQuestionUseCase: UpdateQuestionUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    val _question = MutableLiveData<Questions>()
    val question: LiveData<Questions> get() = _question
    val _notes = MutableLiveData<List<Notes>>()
    val notes: LiveData<List<Notes>> get() = _notes

    val _photos = MutableLiveData<List<Photos>>()
    val photos: LiveData<List<Photos>> get() = _photos

    fun getAnswer(idAnswer: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseAnswer = getAnswerUseCase.execute(idAnswer)
            //         _answer.postValue(responseAnswer)
        }
    }

    fun updateAnswer(answers: Answers) {
        viewModelScope.launch(Dispatchers.IO) {
            updateAnswerUseCase.execute(answers)
        }
    }

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

    fun updateQuestionAndAddAnswer(questions: Questions, answers: Answers) {
        viewModelScope.launch(Dispatchers.IO) {
            updateQuestionsUseCase.execute(questions, answers)
        }
    }

    fun updateListQuestionsAndAddAnswer(questionsIdList: List<String>, answers: Answers) {
        viewModelScope.launch(Dispatchers.IO) {
            updateListQuestionsUseCase.execute(questionsIdList, answers)
        }

    }



    fun getPhotos(questionId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responsePhotos = getPhotosUseCase.execute(questionId)
            _photos.postValue(responsePhotos)
        }
    }

    fun updatePhotos(photos: Photos) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun getNotes(briefcaseId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val notesList = getNotesUseCase.execute(briefcaseId)
            _notes.postValue(notesList)
        }


    }

    fun insertPhoto(uri: Uri, idQuestion: String,contentResolver: ContentResolver ) {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmapPhoto = getBitmap(contentResolver, uri)
      //      val bitmapPhoto = bitmapPhoto1?.let { scaleBitmapbit(it) }
     //       val bitmapPhoto = bitmapResize(contentResolver, uri)

   //         val bitmapPhoto = getSmallBitmap(uri.toString(), 80, 120)
            Log.d("MyLog", "answer viewModel Size: $bitmapPhoto ")
            val photo = bitmapPhoto?.let {
                Photos(
                    photoId = 0,
                    questionId = idQuestion,
                    photoUri = it
                )
            }


            if (photo != null) {
                insertPhotoUseCase.execute(photo)
            }
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
        } catch (e: Exception){
            null
        }
    }


    fun bitmapResize (contentResolver: ContentResolver, fileUri: Uri?): Bitmap? {

        val bitmap = getBitmap(contentResolver,fileUri)
        val inputStream: InputStream? = fileUri?.let { contentResolver.openInputStream(it) }
        val filesize = inputStream?.available()
        Log.d("MyLog", "answer viewModel file uri: $fileUri ")
        inputStream?.close()



        val file = File(fileUri?.toString()
       //     Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
       //     "savedBitmap.png"
        )
        var fos: OutputStream? = null



        if (filesize != null) {
            if (filesize > MAX_IMAGE_SIZE) {
                var streamLength = MAX_IMAGE_SIZE
                var compressQuality = 100
                val bmpStream = ByteArrayOutputStream()
                Log.d("MyLog", "answer viewModel bmpStreamSize: $bmpStream ")
                while (streamLength >= MAX_IMAGE_SIZE) {
                     fos = contentResolver.openOutputStream(fileUri)

                    Log.d("MyLog", "answer viewModel beginingBitmapSize: $filesize ")
                    compressQuality -= 8
     //               val bitmap = BitmapFactory.decodeFile(originalFile.absolutePath, BitmapFactory.Options())

                    bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, fos)
                    streamLength = bmpStream.toByteArray().size
                }
                fos?.close()

     //           FileOutputStream(compressedFile).use {
     //               it.write(bmpStream.toByteArray())
     //           }
            }
        }
        return bitmap

    }


    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val heightRatio = round(height.toFloat() / reqHeight.toFloat()).toInt()
            val widthRatio = round(width.toFloat() / reqWidth.toFloat()).toInt()
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }

    /**
     *Get the scaled picture
     */
    fun getSmallBitmap(filePath: String,reqWidth: Int,reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true // do not load bitmap into memory, only get its basic information
        BitmapFactory.decodeFile(filePath, options)
        val vvv = BitmapFactory.decodeFile(filePath, options)
        Log.d("MyLog", "answer viewModel vvv: $vvv")
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(filePath, options)
    }






    private fun scaleBitmapbit(bitmap: Bitmap): Bitmap {

        val bitmapPixels = bitmap.height * bitmap.width

        Log.d("MyLog", "answer viewModel beginingBitmapSize: $bitmapPixels ")

        if (bitmapPixels > MAX_IMAGE_SIZE) {
            var streamLength = MAX_IMAGE_SIZE
            var compressQuality = 100
            val bmpStream = ByteArrayOutputStream()



            while (streamLength >= MAX_IMAGE_SIZE) {
                bmpStream.use {
                    it.flush()
                    it.reset()
                }
                Log.d("MyLog", "answer viewModel compressQuality Bitmap: $compressQuality")
                compressQuality -= 8
         //       val bitmap = BitmapFactory.decodeFile(originalFile.absolutePath, BitmapFactory.Options())
                bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
                streamLength = bitmap.height * bitmap.width
                Log.d("MyLog", "answer viewModel finish BitmapSize: $streamLength")
            }


        }
        return bitmap
    }








 //   fun insertPhoto(photo: Photos) {
 //       viewModelScope.launch(Dispatchers.IO) {
 //         insertPhotoUseCase.execute(photo)
 //       }
//    }

    fun deletePhoto(photo: Photos) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePhotoUseCase.execute(photo)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    companion object {
        val MAX_IMAGE_SIZE = 660000
    }
}