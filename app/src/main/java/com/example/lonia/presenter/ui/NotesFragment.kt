package com.example.lonia.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.presenter.adapter.NoteAdapter
import com.example.lonia.presenter.factories.NotesViewModelFactory
import com.example.lonia.presenter.viewmodel.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject


class NotesFragment : Fragment() {

    @Inject
    lateinit var notesViewModelFactory: NotesViewModelFactory
    val notesViewModel: NotesViewModel by viewModels { notesViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .notesFragmentInject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addButton: FloatingActionButton = view.findViewById(R.id.bt_note_add)
        val recyclerViewNotes: RecyclerView = view.findViewById(R.id.rv_notes_fragment_recycler)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0)
        val isFromAnswerFragment = arguments?.getInt("answer fragment")
        val answerDate = arguments?.getLong("answer date")
        val answerText = arguments?.getString("answer text")
        val answerKeyPosition = arguments?.getInt("answer key position")
        val significanceLevel = arguments?.getString("significance")


        if (briefcaseId != null) {
            notesViewModel.getNotes(briefcaseId)
        }

        notesViewModel.notesList.observe(this.viewLifecycleOwner) {

            val notesAdapter = NoteAdapter(it)
            recyclerViewNotes.layoutManager = LinearLayoutManager(context)
            recyclerViewNotes.adapter = notesAdapter

            notesAdapter.shortOnClickListener = object : NoteAdapter.ShortOnClickListener {

                override fun ShortClick(noteId: Long, noteValue: String, noteName: String) {

                    if (isFromAnswerFragment == 1) {
                        val bundle = Bundle()
                        bundle.putInt("from notes", 1)
                        bundle.putString("notes fragment", noteValue)
                        bundle.putString("answer text", answerText)
                        if (answerDate != null) {
                            bundle.putLong("answer date", answerDate)
                        }
                        if (answerKeyPosition != null) {
                            bundle.putInt("answer key position", answerKeyPosition)
                        }
                        bundle.putString("significance", significanceLevel)
                        val navController = Navigation.findNavController(view)
                        navController.navigate(R.id.answerFragment, bundle)
                    } else {
                        val bundle = Bundle()
                        bundle.putLong("noteId", noteId)
                        bundle.putString("noteValue", noteValue)
                        bundle.putString("noteName", noteName)
                        val navController = Navigation.findNavController(view)
                        navController.navigate(
                            R.id.action_questionsNotesFragment_to_noteFragment,
                            bundle
                        )
                    }
                }
            }

        }

        addButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("noteId", 0L)
            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_questionsNotesFragment_to_noteFragment)
        }
    }

    companion object {

        fun getInstance() = NotesFragment()
    }

}