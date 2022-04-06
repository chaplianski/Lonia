package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questions
import com.example.testtask.presenter.adapter.QuestionnairesAdapter
import com.example.testtask.presenter.factories.QuestionnairesViewModelFactory
import com.example.testtask.presenter.viewmodel.QuestionnairesViewModel
import java.time.LocalDate
import java.util.*
import javax.inject.Inject


class QuestionnairesFragment : Fragment() {

    @Inject
    lateinit var questionnairesViewModelFactory: QuestionnairesViewModelFactory
    val questionnairesViewModel: QuestionnairesViewModel by viewModels {questionnairesViewModelFactory}



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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenResumed {
            questionnairesViewModel.getQuestionnairesList()
        }


        questionnairesViewModel.questionnaires.observe(this.viewLifecycleOwner, {questinnaries ->
            Log.d("MyLog", "GetList QuestionnariesFragment: $questinnaries")

            val questionnairesAdapter = QuestionnairesAdapter(questinnaries)
            val questionnairesRV = view.findViewById<RecyclerView>(R.id.rv_category)
            questionnairesRV.layoutManager = LinearLayoutManager(context)
            questionnairesRV.adapter = questionnairesAdapter



            questionnairesAdapter.shortOnClickListener =
                object : QuestionnairesAdapter.ShortOnClickListener {
                    override fun ShortClick(title: String, qid: Int) {

                        val builder = AlertDialog.Builder(context)
                        with(builder) {
                            setTitle("Are your sure?")
                            setCancelable(true)
                            setMessage("You check questionnaire $title")
                            setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->

                                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                                val vessel = sharedPref?.getString(Constants.CURRENT_VESSEL, "").toString()
                                val inspectorType = sharedPref?.getString(Constants.CURRENT_INSPECTION_TYPE, "").toString()
                                val inspectorName = sharedPref?.getString(Constants.CURRENT_INSPECTOR_NAME, "").toString()
                                val inspector = sharedPref?.getString(Constants.CURRENT_INSPECTION_SOURCE, "").toString()
                                val category = title
                                val port = sharedPref?.getString(Constants.CURRENT_PORT, "").toString()

                                questionnairesViewModel.saveBriefcase(
                                    vessel, inspectorType, inspectorName, inspector, category, port, qid
                                )


                                navigateToNext()


                            })
                            setNegativeButton(
                                "Cancel",
                                DialogInterface.OnClickListener { dialog, i ->
                                    dialog.cancel()
                                })

                        }
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }
                }
        })

    }

    private fun navigateToNext (){

        val navController = view?.let { Navigation.findNavController(it) }
        navController?.navigate(R.id.action_categoryFragment_to_briefCaseFragment)
    }


}