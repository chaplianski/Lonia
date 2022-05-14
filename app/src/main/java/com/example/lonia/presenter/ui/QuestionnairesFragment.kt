package com.example.lonia.presenter.ui

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.presenter.adapter.QuestionnairesAdapter
import com.example.lonia.presenter.factories.QuestionnairesViewModelFactory
import com.example.lonia.presenter.viewmodel.QuestionnairesViewModel
import com.example.lonia.di.DaggerAppComponent
import kotlinx.coroutines.launch
import javax.inject.Inject


class QuestionnairesFragment : Fragment() {

    @Inject
    lateinit var questionnairesViewModelFactory: QuestionnairesViewModelFactory
    val questionnairesViewModel: QuestionnairesViewModel by viewModels { questionnairesViewModelFactory }


    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .questionnairesFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Questionnaires"
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            questionnairesViewModel.getQuestionnairesList()
        }

        val progressBar =
            view.findViewById<ProgressBar>(R.id.progressBar)
        val errorButton: Button = view.findViewById(R.id.bt_questionnaries_fragment_button)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                questionnairesViewModel.screenState.collect {
                    when (it) {
                        is QuestionnairesViewModel.State.Loading ->
                            progressBar.visibility = View.VISIBLE
                        is QuestionnairesViewModel.State.DownWork ->
                            navigateToNext()
                        is QuestionnairesViewModel.State.Success -> {
                            progressBar.visibility = View.INVISIBLE
                            val questionnairesAdapter =
                                QuestionnairesAdapter(it.questionarries)

                            getRecyclerView(questionnairesAdapter)

                            questionnairesAdapter.shortOnClickListener =
                                object : QuestionnairesAdapter.ShortOnClickListener {
                                    override fun ShortClick(title: String, qid: Int) {

                                        showDialog("questionnaire", title, qid)
                                    }
                                }
                            setupDialog()
                        }
                        is QuestionnairesViewModel.State.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            val questionnairesRV =
                                view?.findViewById<RecyclerView>(R.id.rv_category)
                            questionnairesRV.visibility = View.INVISIBLE
                            val message = it.exception
                            getErrorMessage(message)
                            errorButton.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        errorButton.setOnClickListener {
            val navController = view.let { Navigation.findNavController(it) }
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun getErrorMessage(@StringRes message: Int) {
        val messageTextView = view?.findViewById<TextView>(R.id.tv_questionnaries_fragment_error_message)
        messageTextView?.setText(message)
        messageTextView?.visibility = View.VISIBLE
        messageTextView?.setTextColor(Color.RED)
    }

    private fun navigateToNext() {

        val navController = view?.let { Navigation.findNavController(it) }
        navController?.navigate(R.id.action_categoryFragment_to_briefCaseFragment)
    }

    fun getRecyclerView(rvAdapter: QuestionnairesAdapter) {

        //    val questionnairesAdapter = QuestionnairesAdapter(questinnaries)
        val questionnairesRV = view?.findViewById<RecyclerView>(R.id.rv_category)
        questionnairesRV?.layoutManager = LinearLayoutManager(context)
        questionnairesRV?.adapter = rvAdapter
    }

    fun showDialog(nameItem: String, item: String, qid: Int) {
        val questionnariesDialogFragment = QuestionnariesDialogFragment()
        questionnariesDialogFragment.arguments = bundleOf(
            QuestionnariesDialogFragment.TITLE_KEY_RESPONSE to item,
            QuestionnariesDialogFragment.QUESTIONNARIES_VALUE_ITEM to nameItem,
            QuestionnariesDialogFragment.QID_KEY_RESPONSE to qid
        )
        questionnariesDialogFragment.show(
            parentFragmentManager,
            QuestionnariesDialogFragment.QUESTIONNARIES_REQUEST_KEY
        )
    }

    fun setupDialog() {
        parentFragmentManager.setFragmentResultListener(
            QuestionnariesDialogFragment.QUESTIONNARIES_REQUEST_KEY,
            this,
            FragmentResultListener { _, result ->
                val which = result.getInt(QuestionnariesDialogFragment.QUESTIONNARIES_KEY_RESPONSE)
                val title = result.getString(QuestionnariesDialogFragment.TITLE_KEY_RESPONSE)
                val qid = result.getInt(QuestionnariesDialogFragment.QID_KEY_RESPONSE)

                if (which == DialogInterface.BUTTON_POSITIVE) {

                    if (title != null) {
                        saveBriefcase(title, qid)
                    }
                }
            }
        )
    }

    fun saveBriefcase(title: String, qid: Int) {

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val vessel =
            sharedPref?.getString(Constants.CURRENT_VESSEL, "")
                .toString()
        val inspectorType =
            sharedPref?.getString(Constants.CURRENT_INSPECTION_TYPE, "")
                .toString()
        val inspectorName =
            sharedPref?.getString(Constants.CURRENT_INSPECTOR_NAME, "")
                .toString()
        val inspector =
            sharedPref?.getString(
                Constants.CURRENT_INSPECTION_SOURCE, ""
            ).toString()
        val category = title
        val port =
            sharedPref?.getString(Constants.CURRENT_PORT, "").toString()

        Log.d("My Log", "category: $title, qid: $qid")
        questionnairesViewModel.saveBriefcase(
            vessel,
            inspectorType,
            inspectorName,
            inspector,
            category,
            port,
            qid
        )

    }
}
