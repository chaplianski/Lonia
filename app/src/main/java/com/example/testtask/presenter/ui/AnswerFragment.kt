package com.example.testtask.presenter.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.databinding.FragmentAnswerBinding
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.Questions
import com.example.testtask.presenter.factories.AnswerViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.domain.model.Photos
import com.example.testtask.presenter.adapter.PhotoAdapter
import java.io.File


class AnswerFragment : Fragment() {

    @Inject
    lateinit var answerViewModelFactory: AnswerViewModelFactory
    val answerViewModel: AnswerViewModel by viewModels { answerViewModelFactory }

    var _binding: FragmentAnswerBinding? = null
    val binding get() = _binding!!




    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .answerFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Answer"
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val informationButton = binding.tvAnswerInformation
        val dateAnswer = binding.btAnswerDate
        val answerTextVew = binding.etAnswerContent
        val positiveButton = binding.btAnswerYes
        val negativeButton = binding.btAnswerNo
        val questionTextView = binding.tvAnswerTitle
        val photoTextView = binding.tvAnswerAttachPhoto
        val noteTextVeiw = binding.tvAnswerAttachNote
        val photoRecyclerView = binding.rvAnswerPhotos
        val saveBotton = binding.btAnswerSaveBotton
        val navController = Navigation.findNavController(view)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val question = sharedPref?.getString(Constants.CURRENT_QUESTION, "").toString()
        val questionId = sharedPref?.getString(Constants.CURRENR_QUESTION_ID, "").toString()
        val answerId = sharedPref?.getInt(Constants.CURRENT_ANSWER_ID, 0)


        informationButton.paint.isUnderlineText = true
        photoTextView.paint.isUnderlineText = true
        noteTextVeiw.paint.isUnderlineText = true

        questionTextView.text = question






        if (answerId != null) {
            if (!answerId.equals(0)) {
                answerViewModel.getAnswer(answerId.toLong())
            }
        }
        val formateDate = SimpleDateFormat("dd.MM.yyyy", Locale.US)

        informationButton.setOnClickListener {
            navController.navigate(com.example.testtask.R.id.action_answerFragment_to_myDialog)

        }

        var answerDate: Long = 0

        dateAnswer.setOnClickListener {

            val getData = Calendar.getInstance()
            val dataPicker = DatePickerDialog(
                context!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { datePicker, i, i2, i3 ->
                    val selectData = Calendar.getInstance()
                    selectData.set(Calendar.YEAR, i)
                    selectData.set(Calendar.MONTH, i2)
                    selectData.set(Calendar.DAY_OF_MONTH, i3)
                    val date = formateDate.format(selectData.timeInMillis)
                    dateAnswer.text = date
                    answerDate = selectData.timeInMillis
                },
                getData.get(Calendar.YEAR),
                getData.get(Calendar.MONTH),
                getData.get(Calendar.DAY_OF_MONTH)
            )
            dataPicker.show()
        }

        var currentAnswerId = 0L

        answerViewModel.answer.observe(this.viewLifecycleOwner, { answer ->

            answerTextVew.setText(answer.answer)
            if (!answer.answerDate.equals(0) && !answer.answerDate.equals(null)) {
                val data = answer.answerDate
                currentAnswerId = answer.answerId
                dateAnswer.text = formateDate.format(data)
                answerViewModel.getPhotos(answer.answerId)
            }
        })

        answerViewModel.photos.observe(this.viewLifecycleOwner, {


            val photoAdapter = PhotoAdapter(it)
            photoRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
       //     GridLayoutManager(context, 6, RecyclerView.VERTICAL,false).apply{
        //        photoRecyclerView.layoutManager = this
        //    }
            photoRecyclerView.adapter = photoAdapter

        //    photoAdapter?.updateData(it)

        })

        saveBotton.setOnClickListener {
            val answerValue = answerTextVew.text.toString()
            val questions = Questions(
                questionid = questionId,
                comment = "",
                dateOfInspection = "",
                answer = 0,
                questioncode = "",
                question = "",
                commentForQuestion = "",
                categoryid = "",
                origin = "",
                categorynewid = "",
                isAnswered = true,
                images = 0,
                briefCaseId = 0
            )
            val answer = Answers(
                answer = answerValue,
                answerDate = answerDate,
                answerId = currentAnswerId,
                answerChoice = true,

            )

            if (currentAnswerId != 0L) {
                answerViewModel.updateAnswer(answer)
            } else {
                answerViewModel.updateQuestionAndAddAnswer(questions, answer)
            }

            navController.navigate(com.example.testtask.R.id.briefCaseFragment)
        }

        negativeButton.setOnClickListener {
            navController.navigate(com.example.testtask.R.id.action_answerFragment_to_questionsFragment)
        }

        photoTextView.setOnClickListener {
            addChoosePhotoDirectionDialog()
        }



    }

    val getPhotoGallary = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->

        savePhotoToAnswer(imageUri)
        //   photoAdapter.updateData(it)

    }

    private fun savePhotoToAnswer(imageUri: Uri?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val answerId = sharedPref?.getInt(Constants.CURRENT_ANSWER_ID, 0)

        val newPhoto = answerId?.toLong()?.let {
            Photos(
                photoId = 0,
                answerId = it,
                photoUri = imageUri.toString()
            )
        }

        Log.d("My Log", "answer fragment new photo: $newPhoto")
        if (newPhoto != null) {
            answerViewModel.insertPhoto(newPhoto)
            answerViewModel.getPhotos(answerId.toLong())
            Log.d("My Log", "answer fragment insert photo")
        }
    }

    var resultUri :Uri? = null

    private val getPhotoCapture = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->

        savePhotoToAnswer(resultUri)
        Log.d("My Log", "answer fragment result photo capture: $success")

    }

    private fun addChoosePhotoDirectionDialog() {
        val photoDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
            resultUri = activity!!.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues())
        Log.d("My Log", "answer fragment uri: $resultUri")
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Choose variant of adding photo")
        builder.setItems(photoDialogItems) { _, position ->
            when (position) {
                0 -> getPhotoGallary.launch(MIMETYPE_IMAGES) //choosePhotoFromGallary()
                1 -> getPhotoCapture.launch(resultUri) //arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA))
                //takePhotoFromCamera()
            }
        }
        builder.show()
    }


    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, 11)
    }

    private fun takePhotoFromCamera() {
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
/*
    fun hasCameraPermission() = context?.let {
        ContextCompat.checkSelfPermission(
            it,
            android.Manifest.permission.CAMERA
        )
    } == PackageManager.PERMISSION_GRANTED

    fun createImageFile(): File {
        val timeStamp = SimpleDateFormat.getDateTimeInstance().format(Date())
        val storageDir = getPicturesDirectory()

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentImagePath = absolutePath
        }
    }

    val uri = FileProvider.getUriForFile (context, "file_provider", file)
*/
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 11) {
                if (data != null) {
                    Uri contentURI = data . getData ();
                    try {
                        Bitmap bitmap = MediaStore . Images . Media . getBitmap (getActivity().getContentResolver(), contentURI);
                        //                    String path = saveImage(bitmap);
                        //                    Toast.makeText(getActivity(), "Image Saved!", Toast.LENGTH_SHORT).show();
                        iv_froncnic.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
                {
                    Toast.makeText(getActivity(), "Data not found", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }

  */


    fun showCommentDialog() {
        val commentDialog = CommentFragment()
        commentDialog.show(parentFragmentManager, CommentFragment.COMMENT_TAG)
    }

    fun setupCommentDialogListener() {
        parentFragmentManager.setFragmentResultListener(
            CommentFragment.REQUEST_KEY,
            this.viewLifecycleOwner,
            FragmentResultListener { _, result ->
                val which = result.getInt(CommentFragment.COMMENT_TAG)
                if (which == DialogInterface.BUTTON_POSITIVE) Log.d("My Log", "Close comment")
            })
    }

    companion object {
        const val MIMETYPE_IMAGES = "image/*"
    }


}

