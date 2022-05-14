package com.example.lonia.presenter.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.domain.model.BriefcasesAndQuestions
import com.example.lonia.presenter.adapter.BriefCaseAdapter
import com.example.lonia.presenter.factories.BriefcaseViewModelFactory
import com.example.lonia.presenter.viewmodel.BriefCaseViewModel
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.presenter.ui.dialogs.BriefcaseAnswerDialog
import com.example.lonia.presenter.ui.dialogs.BriefcaseAnswerDialog.Companion.ANSWER_BRIEFCASE_ID
import com.example.lonia.presenter.ui.dialogs.BriefcaseAnswerDialog.Companion.ANSWER_KEY_RESPONSE
import com.example.lonia.presenter.ui.dialogs.BriefcaseAnswerDialog.Companion.ANSWER_REQUEST_KEY
import com.example.lonia.presenter.ui.dialogs.BriefcaseErrorSaveDialog
import com.example.lonia.presenter.ui.dialogs.BriefcaseSuccessSaveDialog
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import javax.inject.Inject


class BriefCaseFragment : Fragment() {

    @Inject
    lateinit var briefcaseViewModelFactory: BriefcaseViewModelFactory
    val briefcaseViewModel: BriefCaseViewModel by viewModels { briefcaseViewModelFactory }
    var briefcaseAdapter: BriefCaseAdapter? = null

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .briefcaseFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Briefcases"
        activitySupport.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activitySupport.supportActionBar?.setDisplayShowHomeEnabled(false)

        return inflater.inflate(R.layout.fragment_briefcase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val addNewBriefCaseButton: FloatingActionButton = view.findViewById(R.id.bt_briefcase_add)
        val progressBar: ProgressBar = view.findViewById(R.id.pb_briefcase_progressbar)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)


        briefcaseViewModel.getBriefCaseList()

        val bottomNavigation: BottomNavigationView = view.findViewById(R.id.bottom_navigation)


        val briefcaseButton = view.findViewById<BottomNavigationItemView>(R.id.page_briefcase)
        val answersButton = view.findViewById<BottomNavigationItemView>(R.id.page_answer)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                briefcaseViewModel.screenState.collect {
                    when(it){
                        is BriefCaseViewModel.BriefcaseState.Loading ->{
                            progressBar.visibility = View.VISIBLE
                        }
                        is BriefCaseViewModel.BriefcaseState.ShowData -> {
                            progressBar.visibility = View.GONE
                            showBriefcases(
                                it.briefcasesAndQuestionsList,
                                view,
                                briefcaseButton,
                                sharedPref,
                                navController,
                                answersButton
                            )
                            bottomNavigation.setOnItemSelectedListener { item ->
                                when (item.itemId) {
                                    R.id.page_briefcase -> {
                                        addNewBriefCaseButton.visibility = View.VISIBLE

                                        val newList = it.briefcasesAndQuestionsList.map { task ->
                                            task.copy(isVisible = false)
                                        }
                                        briefcaseAdapter?.updateData(newList)

                                        true
                                    }
                                    R.id.page_answer -> {
                                        addNewBriefCaseButton.visibility = View.INVISIBLE

                                        val newList = it.briefcasesAndQuestionsList.map { task ->
                                            task.copy(isVisible = true)
                                        }

                                        briefcaseAdapter?.updateData(newList)

                                        true
                                    }
                                    else -> false
                                }
                            }
                        }
                        is BriefCaseViewModel.BriefcaseState.Success -> {
                            showSuccessDialog()
                        }
                        is  BriefCaseViewModel.BriefcaseState.Error -> {

                            progressBar.visibility = View.GONE
                            val message = it.exception
                            showErrorDialog(getString(message))
                        }
                    }
                    setupErrorDialog()
                }

            }

        }



        addNewBriefCaseButton.setOnClickListener {
            navController.navigate(R.id.action_briefCaseFragment_to_vesselsFragment)
        }
    }

    private fun showBriefcases(
        it: List<BriefcasesAndQuestions>,
        view: View,
        briefcaseButton: BottomNavigationItemView,
        sharedPref: SharedPreferences?,
        navController: NavController,
        answersButton: BottomNavigationItemView
    ) {
        briefcaseAdapter = BriefCaseAdapter(it)
        val briefcaseRV = view.findViewById<RecyclerView>(R.id.rv_briefcase)
        briefcaseRV.layoutManager = LinearLayoutManager(context)
        briefcaseRV.adapter = briefcaseAdapter

        briefcaseAdapter?.shortOnClickListener =
            object : BriefCaseAdapter.ShortOnClickListener {

                override fun ShortClick(briefCase: BriefcasesAndQuestions) {

                    if (briefcaseButton.isSelected) {
                        sharedPref?.edit()
                            ?.putLong(Constants.CURRENT_BRIEFCASE, briefCase.briefCaseId)
                            ?.apply()
                        navController.navigate(R.id.action_briefCaseFragment_to_questionsNotesFragment)
                    }
                    if (answersButton.isSelected) {
                        if (briefCase.total == briefCase.answered){
                            showDialog(briefCase.briefCaseId)
                        }else{
                            sharedPref?.edit()
                                ?.putLong(Constants.CURRENT_BRIEFCASE, briefCase.briefCaseId)
                                ?.apply()
                            navController.navigate(R.id.action_briefCaseFragment_to_answersFragment)
                        }
                        setupDialog()
                    }

                }
            }
    }


    fun showDialog(briefcaseId: Long) {
        val briefcaseAnswerDialog = BriefcaseAnswerDialog()
        briefcaseAnswerDialog.arguments = bundleOf(
            BriefcaseAnswerDialog.ANSWER_BRIEFCASE_ID to briefcaseId
        )
        briefcaseAnswerDialog.show(
            parentFragmentManager,
            BriefcaseAnswerDialog.ANSWER_REQUEST_KEY
        )
    }

    fun setupDialog() {
        parentFragmentManager.setFragmentResultListener(
            BriefcaseAnswerDialog.ANSWER_REQUEST_KEY,
            this,
            FragmentResultListener { _, result ->
                val which = result.getInt(BriefcaseAnswerDialog.ANSWER_KEY_RESPONSE)
                val briefcaseId = result.getLong(BriefcaseAnswerDialog.ANSWER_BRIEFCASE_ID)
                val navController = view?.let { Navigation.findNavController(it) }
                when(which){
                    0 -> {
                       Log.d("MyLog", "briefcase fragment : save and hold")
                        briefcaseViewModel.saveBriefcase(briefcaseId)
                    }
                    1 -> {
                        Log.d("MyLog", "briefcase fragment : save and delete")
                        briefcaseViewModel.saveBriefcase(briefcaseId)
                    }
                    2 -> {
                        Log.d("MyLog", "briefcase fragment : look")
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                        sharedPref?.edit()
                            ?.putLong(Constants.CURRENT_BRIEFCASE, briefcaseId)
                            ?.apply()
                        navController?.navigate(R.id.action_briefCaseFragment_to_answersFragment)
                    }
                }

            }
        )
    }

    fun showSuccessDialog() {
        val briefcaseSuccessDialog = BriefcaseSuccessSaveDialog()
        briefcaseSuccessDialog.show(
            parentFragmentManager,
            BriefcaseSuccessSaveDialog.SUCCESS_REQUEST_KEY
        )
    }

    fun showErrorDialog(messageError: String) {
        val briefcaseErrorDialog = BriefcaseErrorSaveDialog()
        briefcaseErrorDialog.arguments = bundleOf(
            BriefcaseErrorSaveDialog.MESSAGE_ERROR to messageError
        )
        briefcaseErrorDialog.show(
            parentFragmentManager,
            BriefcaseErrorSaveDialog.ERROR_REQUEST_KEY
        )
    }

    fun setupErrorDialog() {
        parentFragmentManager.setFragmentResultListener(
            BriefcaseErrorSaveDialog.ERROR_REQUEST_KEY,
            this,
            FragmentResultListener { _, result ->
                val which = result.getInt(BriefcaseErrorSaveDialog.ERROR_KEY_RESPONSE)
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val briefcaseId = sharedPref?.getLong(Constants.CURRENT_BRIEFCASE, 0L)
                if (which == -1) briefcaseId?.let { briefcaseViewModel.saveBriefcase(it) }
            }
        )
    }


}