package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.databinding.FragmentAnswerBinding
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Photos
import com.example.testtask.domain.model.Questions
import com.example.testtask.presenter.adapter.PhotoAdapter
import com.example.testtask.presenter.factories.AnswerViewModelFactory
import com.example.testtask.presenter.viewmodel.AnswerViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AnswerFragment : Fragment() {

    @Inject
    lateinit var answerViewModelFactory: AnswerViewModelFactory
    val answerViewModel: AnswerViewModel by viewModels { answerViewModelFactory }

    var _binding: FragmentAnswerBinding? = null
    val binding get() = _binding!!

    private lateinit var photoPicker: PhotoPicker

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoPicker =
            PhotoPicker(context!!, requireActivity().activityResultRegistry) { uri ->
                savePhotoToAnswer(uri)
                Log.d("My Log", "answer fragment uri: $uri")
                // if (uri == null) return@PhotoPicker
                //  FileUtils.getBitmap(requireActivity().contentResolver,uri)
            }

        val informationButton = binding.tvAnswerInformation
        val dateAnswer = binding.btAnswerDate
        val answerTextVew = binding.etAnswerText
        val positiveButton = binding.btAnswerYes
        val negativeButton = binding.btAnswerNo
        val questionTextView = binding.tvAnswerTitle
        val photoTextView = binding.tvAnswerAttachPhoto
        val noteTextVeiw = binding.tvAnswerAttachNote
        val saveBotton = binding.btAnswerSaveBotton
        val navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val question = sharedPref?.getString(Constants.CURRENT_QUESTION, "").toString()
        val questionId = sharedPref?.getString(Constants.CURRENR_QUESTION_ID, "").toString()
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)
        val answerId = sharedPref?.getInt(Constants.CURRENT_ANSWER_ID, 0)
        val buttonSelectionError = binding.tvAnswerButtonError
        val answerTextField = binding.etAnswerField
        val listQuestionsId = arguments?.getStringArray(Constants.LIST_QUESTIONS_ID)?.toList()
        var answerKeyPosition = 0
        var answerDate: Long = 0
        var currentAnswerId = 0L

        positiveButton.isEnabled = true
        negativeButton.isEnabled = true

        informationButton.paint.isUnderlineText = true
        photoTextView.paint.isUnderlineText = true
        noteTextVeiw.paint.isUnderlineText = true

        val formateDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        // ***** handling multiple questions *******

        if (!listQuestionsId?.toList().isNullOrEmpty()) {
            questionTextView.text = "Answer to the question added to the list"
        } else {
            questionTextView.text = question
        }

        // ***** update answer *****

        if (answerId != null) {
            if (!answerId.equals(0)) {
                answerId?.toLong()?.let { answerViewModel.getAnswer(it) }
            }
        }

        // ***** Get comment *****

        informationButton.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_myDialog)
        }

        // ***** Get data from DataPicker *****

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
                    dateAnswer.setTextColor(Color.BLACK)
                },
                getData.get(Calendar.YEAR),
                getData.get(Calendar.MONTH),
                getData.get(Calendar.DAY_OF_MONTH)
            )
            dataPicker.show()
        }

        val isFromNotes = arguments?.getInt("from notes")
        if (isFromNotes == 1) {

            val noteText = arguments?.getString("notes fragment")
            val answer = arguments?.getString("answer text")
            answerTextVew.setText("$answer \n$noteText")
            val date = arguments?.getLong("answer date")
            if (date == 0L) {
                dateAnswer.text = "DATE OF VERIFICATION"
            } else {
                dateAnswer.text = formateDate.format(date)
            }

            val keyPosition = arguments?.getInt("answer key position")
            Log.d(
                "My Log",
                "fron answer fragment: answerData: $dateAnswer, answerText: $answer, answerKey: $keyPosition"
            )

            when (keyPosition) {
                1 -> {
                    positiveButton.isSelected = true
                    positiveButton.setTextColor(Color.WHITE)
                }
                2 -> {
                    negativeButton.isSelected = true
                    negativeButton.setTextColor(Color.WHITE)
                }

                else -> {
                    positiveButton.isSelected = false
                    negativeButton.isSelected = false
                }
            }
        }


        //***** Select button (Yes / No) *****

        positiveButton.setOnClickListener {
            answerKeyPosition = 1
            positiveButton.isSelected = true
            negativeButton.isSelected = false
            buttonSelectionError.visibility = View.GONE
            positiveButton.setTextColor(Color.WHITE)
            negativeButton.setTextColor(Color.BLACK)
        }

        negativeButton.setOnClickListener {
            answerKeyPosition = 2
            negativeButton.isSelected = true
            positiveButton.isSelected = false
            negativeButton.setTextColor(Color.WHITE)
            buttonSelectionError.visibility = View.GONE
            positiveButton.setTextColor(Color.BLACK)
        }

        // ***** Observe answer on layout *****

        answerViewModel.question.observe(this.viewLifecycleOwner, { question ->

            // Condition not empty views of answer *****
            if(question.isAnswered)
                answerTextVew.setText(question.commentForQuestion)
            answerDate = question.dateOfInspection.toLong()
            val data = question.dateOfInspection.toLong()
      //      currentAnswerId = answer.answerId
            dateAnswer.text = formateDate.format(data)
            answerViewModel.getPhotos(question.questionid)


/*
            if (!answer.answerDate.equals(0) && !answer.answerDate.equals(null)) {
                answerTextVew.setText(answer.answer)
                answerDate = answer.answerDate
                val data = answer.answerDate
                currentAnswerId = answer.answerId
                dateAnswer.text = formateDate.format(data)
                answerViewModel.getPhotos(answer.answerId)
            }
            */

        })

        // ****** Observe photos list *****

        answerViewModel.photos.observe(this.viewLifecycleOwner, {

            if(!questionId.equals("")){
                getRecyclerView(it, view, questionId)
            }


   //         if (answerId != null) {
   //             getRecyclerView(it, view, answerId.toLong())
   //         }
        })

        saveBotton.setOnClickListener {

            // ****** Check no empty required field ******

            // Check answer data
            if (answerDate == 0L) {
                dateAnswer.text = "Please enter the date"
                dateAnswer.setTextColor(Color.RED)

                // Check answer text
            } else if (answerTextVew.text?.isBlank() == true) {
                answerTextField.error = "Please enter email"

                // Check button select
            } else if (!positiveButton.isSelected && !negativeButton.isSelected) {
                buttonSelectionError.visibility = View.VISIBLE

                // ***** Save data if answer for multiple list questions *****

            } else if (!listQuestionsId?.toList().isNullOrEmpty()) {
                val listId = listQuestionsId?.toList()
                val answerValue = answerTextVew.text.toString()
                if (listId != null) {
                    for (questionId in listId){
                        val questions = Questions(
                            questionid = questionId,
                            comment = "",
                            dateOfInspection = answerDate.toString(),
                            answer = answerKeyPosition,
                            questioncode = "",
                            question = "",
                            commentForQuestion = answerValue,
                            categoryid = "",
                            origin = "",
                            categorynewid = "",
                            isAnswered = true,
                            images = 0,
                            briefCaseId = 0
                        )
                        answerViewModel.updateQuestion(questions)

                    }
                }

 //               val answer = Answers(
 //                   answer = answerValue,
 //                   answerDate = answerDate,
 //                   answerId = currentAnswerId,
 //                   answerChoice = true,
 //               )
 //               if (listId != null) {
 //                   answerViewModel.updateListQuestionsAndAddAnswer(listId, answer)
 //               }
                navController.navigate(R.id.briefCaseFragment)

                // ***** Save data if answer for one question

            } else {
                val answerValue = answerTextVew.text.toString()
                val questions = Questions(
                    questionid = questionId,
                    comment = "",
                    dateOfInspection = answerDate.toString(),
                    answer = answerKeyPosition,
                    questioncode = "",
                    question = "",
                    commentForQuestion = answerValue,
                    categoryid = "",
                    origin = "",
                   categorynewid = "",
                    isAnswered = true,
                    images = 0,
                    briefCaseId = 0
                )
    //            val answer = Answers(
    //                answer = answerValue,
    //                answerDate = answerDate,
    //                answerId = currentAnswerId,
    //                answerChoice = true,
    //            )

                // ***** Check existing answer *****

  //              if(questionId != ""){
                    answerViewModel.updateQuestion(questions)
  //              }

  //              if (currentAnswerId != 0L) {
  //                  answerViewModel.updateAnswer(answer)
  //              } else {
  //                  answerViewModel.updateQuestionAndAddAnswer(questions, answer)
 //               }
                navController.navigate(R.id.briefCaseFragment)
            }
        }



        // ***** Add photos to answer *****

        photoTextView.setOnClickListener {
            addChoosePhotoDirectionDialog()
        }

        // ***** Go to notes fragment *****

        noteTextVeiw.setOnClickListener {

            if (briefcaseId != null) {
                answerViewModel.getNotes(briefcaseId)
            }
            answerViewModel.notes.observe(this.viewLifecycleOwner, {

                if(it.isEmpty()){
                    Toast.makeText(context, "There are currently no notes", Toast.LENGTH_SHORT).show()
                }else{
                    val answerText = answerTextVew.text.toString()
                    val bundle = Bundle()
                    bundle.putInt("answer fragment", 1)
                    bundle.putString("answer text", answerTextVew.text.toString())
                    bundle.putInt("answer key position", answerKeyPosition)
                    bundle.putLong("answer date", answerDate)

                    val navController = Navigation.findNavController(view)
                    navController.navigate(R.id.action_answerFragment_to_notesFragment, bundle)
                }
            })



        }

        answerTextVew.doOnTextChanged { inputText, _, _, _ ->
            if (inputText?.length!! > 0) {
                answerTextField.error = null
            }
        }
    }

    private fun getRecyclerView(photos: List<Photos>, view: View, questionId: String) {
        val photoAdapter = PhotoAdapter(photos)
        val photoRecyclerView = binding.rvAnswerPhotos
        photoRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        photoRecyclerView.adapter = photoAdapter

        photoAdapter.shortOnClickListener = object : PhotoAdapter.ShortOnClickListener {
            override fun shortClick(photo: Photos) {
                //            Log.d("My Log", "answer fragment shot click")
                //            val bundle = Bundle()
                //             bundle.putString("photoUri", photo.photoUri)
                //             Log.d("My Log", "answer fragment shot click, photoUri: ${photo.photoUri}")
                //             val navController = Navigation.findNavController(view)
                //             navController.navigate(R.id.action_answerFragment_to_photoViewDialog, bundle)
            }
        }

        photoAdapter.longOnClickListener = object : PhotoAdapter.LongOnClickListener {
            override fun longClick(photo: Photos) {

                val builder = AlertDialog.Builder(context)
                builder.setTitle("Are you sure, that want delete image?")
                builder.setPositiveButton("Yes") { dialog, id ->
                    answerViewModel.deletePhoto(photo)
                    answerViewModel.getPhotos(questionId)
                }
                builder.setNegativeButton("No", null)
                builder.show()
            }
        }
    }

    val getPhotoGallary =
        registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            savePhotoToAnswer(imageUri)
        }







    private fun savePhotoToAnswer(imageUri: Uri?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val questionId = sharedPref?.getString(Constants.CURRENR_QUESTION_ID, "")

 //       val newPhoto = questionId?.let {
 //           Photos(
 //               photoId = 0,
 //               questionId = it,
 //               photoUri = imageUri.toString()
//            )
 //       }
        val contentResolver = context?.getContentResolver()

        //      Log.d("My Log", "answer fragment new photo: $newPhoto")
 //       if (newPhoto != null) {
 //           if (imageUri != null) {
        if (imageUri != null) {
            if (questionId != null) {
                if (contentResolver != null) {
                    answerViewModel.insertPhoto(imageUri, questionId, contentResolver)
                }
            }
        }
 //           }
 //       if (questionId != null) {
 //           answerViewModel.getPhotos(questionId)
  //      }
            //        Log.d("My Log", "answer fragment insert photo")
 //       }
    }

    var resultUri: Uri? = null

    private val getPhotoCapture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->

            savePhotoToAnswer(resultUri)


            Log.d("My Log", "answer fragment result photo capture: $success")

        }

    private fun addChoosePhotoDirectionDialog() {
        val photoDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
    //    resultUri = activity!!.contentResolver.insert(
    //        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
    //        ContentValues()
    //    )
    //    Log.d("My Log", "answer fragment uri: $resultUri")
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Choose variant of adding photo")
        builder.setItems(photoDialogItems) { _, position ->
            when (position) {
                0 -> photoPicker.pickPhoto() //getPhotoGallary.launch(MIMETYPE_IMAGES) //choosePhotoFromGallary()
                1 -> photoPicker.takePhoto() //getPhotoCapture.launch(resultUri) //arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA))
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

