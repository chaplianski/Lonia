package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.Notes
import com.example.testtask.presenter.factories.NoteViewModelFactory
import com.example.testtask.presenter.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class NoteFragment : Fragment() {

    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory
    val noteViewModel: NoteViewModel by viewModels { noteViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .noteFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteContent: EditText = view.findViewById(R.id.et_note_fragment_note)
        val buttonNote: Button = view.findViewById(R.id.bt_note_fragment_save)
        val noteTitle: TextView = view.findViewById(R.id.tv_note_fragment_title)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)
        val idNote = arguments?.getLong("noteId", 0)

        Log.d("My Log", "note fragment noteId; $idNote")
        val valueNote = arguments?.getString("noteValue", "")
        val nameNote = arguments?.getString("noteName", "")

        var content = ""
        var noteName = ""

        if (idNote != 0L && idNote != null) {
            noteContent.setText(valueNote)
            noteTitle.text = "Edit note"
            noteName = nameNote.toString()
        } else {
            noteTitle.text = "Add note"
            val formateDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val formateTime = SimpleDateFormat("HH-mm", Locale.getDefault())
            val currentData = Date()
            val dateName = formateDate.format(currentData)
            val currentTime = formateTime.format(currentData)
            noteName = "$dateName-$currentTime"
        }

        buttonNote.setOnClickListener {
            content = noteContent.text.toString()

            if (idNote != 0L && idNote != null) {
                val oldNote = Notes(
                    noteId = idNote,
                    noteValue = content,
                    briefcaseId = briefcaseId!!,
                    noteName = nameNote!!
                )
                noteViewModel.updateNote(oldNote)
            } else {

                val newNote = briefcaseId?.let { it1 ->
                    Notes(
                        noteId = 0,
                        noteValue = content,
                        briefcaseId = it1,
                        noteName = noteName
                    )
                }
                if (newNote != null) {
                    noteViewModel.addNote(newNote)
                }
            }

            val bundle = Bundle()
            bundle.putInt("note", 1)
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_noteFragment_to_questionsNotesFragment, bundle)
        }

    }
}


