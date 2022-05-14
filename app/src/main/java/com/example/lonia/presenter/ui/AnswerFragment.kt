package com.example.lonia.presenter.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lonia.R
import com.example.lonia.databinding.FragmentAnswerBinding
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.domain.model.Photos
import com.example.lonia.domain.model.Questions
import com.example.lonia.presenter.adapter.PhotoAdapter
import com.example.lonia.presenter.factories.AnswerViewModelFactory
import com.example.lonia.presenter.viewmodel.AnswerViewModel
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

        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Answer"
        activitySupport.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activitySupport.supportActionBar?.setDisplayShowHomeEnabled(false)



        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoPicker =
            PhotoPicker(context!!, requireActivity().activityResultRegistry) { uri ->
                savePhotoToAnswer(uri)
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
        val lowButton = binding.btAnswerFragmentLow
        val mediumButton = binding.btAnswerFragmentMedium
        val hightButton = binding.btAnswerFragmentHight
        val navController = Navigation.findNavController(view)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val question = sharedPref?.getString(Constants.CURRENT_QUESTION, "").toString()
        val questionId = sharedPref?.getString(Constants.CURRENT_QUESTION_ID, "").toString()
        val isAnswered = sharedPref?.getBoolean(Constants.CURRENT_ISANSWERED, false)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)
        val informationValue = sharedPref?.getString(Constants.CURRENT_COMMENT, "")
        val buttonSelectionError = binding.tvAnswerButtonError
        val answerTextField = binding.etAnswerField
        val listQuestionsId = arguments?.getStringArray(Constants.LIST_QUESTIONS_ID)?.toList()
        var answerKeyPosition = 0
        var answerDate: Long = 0
        var significanceLevel = ""
        val isFromNotes = arguments?.getInt("from notes")

         if (informationValue.isNullOrEmpty()){
             informationButton.visibility = View.INVISIBLE
         }


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

        Log.d("My Log", "from answer fragment: isAnswered: $isAnswered")

        if (isAnswered == true && isFromNotes != 1) {
            answerViewModel.getQuestion(questionId)

            Log.d(
                "My Log",
                "from answer fragment: isAnswered: $isAnswered isFromNotes: $isFromNotes"
            )
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


        // ***** Get data from Notes fragment ***


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
            val significanceValue = arguments?.getString("significance")
            if (date != null) {
                answerDate = date
            }
            if (keyPosition != null) {
                answerKeyPosition = keyPosition
            }
            if (significanceValue != null){
                significanceLevel = significanceValue
            }

            when (keyPosition) {
                1 -> {
                    positiveButton.isSelected = true
                    negativeButton.isSelected = false
                    positiveButton.setTextColor(Color.WHITE)
                }
                2 -> {
                    positiveButton.isSelected = false
                    negativeButton.isSelected = true
                    negativeButton.setTextColor(Color.WHITE)
                }

                else -> {
                    positiveButton.isSelected = false
                    negativeButton.isSelected = false
                }
            }

            when (significanceValue){
                "Low" -> {
                    lowButton.isSelected = true
                    mediumButton.isSelected = false
                    hightButton.isSelected = false
                    lowButton.setTextColor(Color.WHITE)
                }
                "Medium" -> {
                    lowButton.isSelected = false
                    mediumButton.isSelected = true
                    hightButton.isSelected = false
                    mediumButton.setTextColor(Color.WHITE)
                }
                "Hight" -> {
                    lowButton.isSelected = false
                    mediumButton.isSelected = false
                    hightButton.isSelected = true
                    hightButton.setTextColor(Color.WHITE)
                }
                else -> {
                    lowButton.isSelected = false
                    mediumButton.isSelected = false
                    hightButton.isSelected = false
                }
            }

            answerViewModel.getPhotos(questionId)
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

        // ***** Select significance level button ******

        lowButton.setOnClickListener {
            significanceLevel = "Low"
            lowButton.isSelected = true
            mediumButton.isSelected = false
            hightButton.isSelected = false
            lowButton.setTextColor(Color.WHITE)
            mediumButton.setTextColor(Color.BLACK)
            hightButton.setTextColor(Color.BLACK)
        }

        mediumButton.setOnClickListener {
            significanceLevel = "Medium"
            mediumButton.isSelected = true
            lowButton.isSelected = false
            hightButton.isSelected = false
            mediumButton.setTextColor(Color.WHITE)
            lowButton.setTextColor(Color.BLACK)
            hightButton.setTextColor(Color.BLACK)
        }

        hightButton.setOnClickListener {
            significanceLevel = "Hight"
            hightButton.isSelected = true
            lowButton.isSelected = false
            mediumButton.isSelected = false
            hightButton.setTextColor(Color.WHITE)
            lowButton.setTextColor(Color.BLACK)
            mediumButton.setTextColor(Color.BLACK)


        }

        // ***** Observe answer on layout *****

        answerViewModel.question.observe(this.viewLifecycleOwner) { question ->

            // Condition not empty views of answer *****
            if (question.isAnswered) {
                answerTextVew.setText(question.commentForQuestion)
                answerDate = question.dateOfInspection.toLong()
                val data = question.dateOfInspection.toLong()
                //      currentAnswerId = answer.answerId
                dateAnswer.text = formateDate.format(data)
                if (question.answer == 1) {
                    positiveButton.isSelected = true
                    negativeButton.isSelected = false
                    positiveButton.setTextColor(Color.WHITE)
                } else {
                    positiveButton.isSelected = false
                    negativeButton.isSelected = true
                    negativeButton.setTextColor(Color.WHITE)
                }
                answerKeyPosition = question.answer

                Log.d("MyLog", "answer fragment answer question: $question ")
                when (question.significance) {
                    "Low" -> {
                        lowButton.isSelected = true
                        mediumButton.isSelected = false
                        hightButton.isSelected = false
                        lowButton.setTextColor(Color.WHITE)
                    }
                    "Medium" -> {
                        lowButton.isSelected = false
                        mediumButton.isSelected = true
                        hightButton.isSelected = false
                        mediumButton.setTextColor(Color.WHITE)
                    }
                    "Hight" -> {
                        lowButton.isSelected = false
                        mediumButton.isSelected = false
                        hightButton.isSelected = true
                        hightButton.setTextColor(Color.WHITE)
                    }
                    else -> {
                        lowButton.isSelected = false
                        mediumButton.isSelected = false
                        hightButton.isSelected = false
                    }
                }
                significanceLevel = question.significance
                answerViewModel.getPhotos(question.questionid)
            }
        }

        // ****** Observe photos list *****

        answerViewModel.photos.observe(this.viewLifecycleOwner) {

            if (!questionId.equals("")) {
                getRecyclerView(it, view, questionId)
            }
        }

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
                    for (questionId in listId) {
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
                            briefCaseId = 0,
                            significance = significanceLevel
                        )
                        answerViewModel.updateQuestion(questions)
                    }
                }

                navController.navigate(R.id.briefCaseFragment)

                // ***** Save data if answer for one question

            } else {
                Log.d("MyLog", "answer fragment answer key position: $answerKeyPosition sign state: ${significanceLevel} ")
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
                    briefCaseId = 0,
                    significance = significanceLevel
                )
                answerViewModel.updateQuestion(questions)

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
            answerViewModel.notes.observe(this.viewLifecycleOwner) {

                if (it.isEmpty()) {
                    Toast.makeText(context, "There are currently no notes", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val answerText = answerTextVew.text.toString()
                    val bundle = Bundle()
                    bundle.putInt("answer fragment", 1)
                    bundle.putString("answer text", answerTextVew.text.toString())
                    bundle.putInt("answer key position", answerKeyPosition)
                    bundle.putLong("answer date", answerDate)
                    bundle.putString("significance", significanceLevel)

                    val navController = Navigation.findNavController(view)
                    navController.navigate(R.id.action_answerFragment_to_notesFragment, bundle)
                }
            }
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
                val fullScreenImage = binding.ivAnswerFragmentFullImage
                fullScreenImage.visibility = View.VISIBLE
                val image = photo.photoUri
                Glide.with(view).load(image)
                    .centerCrop()
                    .into(fullScreenImage)

                fullScreenImage.setOnClickListener {
                    fullScreenImage.visibility = View.GONE
                }

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

    private fun savePhotoToAnswer(imageUri: Uri?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val questionId = sharedPref?.getString(Constants.CURRENT_QUESTION_ID, "")

        val contentResolver = context?.getContentResolver()

        if (imageUri != null) {
            if (questionId != null) {
                if (contentResolver != null) {
                    answerViewModel.insertPhoto(imageUri, questionId, contentResolver)
                }
            }
        }
    }

    private fun addChoosePhotoDirectionDialog() {

        val photoDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Choose variant of adding photo")
        builder.setItems(photoDialogItems) { _, position ->
            when (position) {
                0 -> photoPicker.pickPhoto()
                1 -> photoPicker.takePhoto()
            }
        }
        builder.show()
    }
}

