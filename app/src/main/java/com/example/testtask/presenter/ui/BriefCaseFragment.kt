package com.example.testtask.presenter.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.BriefcasesAndQuestions
import com.example.testtask.presenter.adapter.BriefCaseAdapter
import com.example.testtask.presenter.factories.BriefcaseViewModelFactory
import com.example.testtask.presenter.viewmodel.BriefCaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject


class BriefCaseFragment : Fragment() {

    @Inject
    lateinit var briefcaseViewModelFactory: BriefcaseViewModelFactory
    val briefcaseViewModel: BriefCaseViewModel by viewModels { briefcaseViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .briefcaseFragentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Briefcases"


    //    activity?.title = "Briefcases"
        return inflater.inflate(R.layout.fragment_briefcase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val addNewBriefCaseButton: FloatingActionButton = view.findViewById(R.id.bt_briefcase_add)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        briefcaseViewModel.getBriefCaseList()

        val bottomNavigation: BottomNavigationView = view.findViewById(R.id.bottom_navigation)


        val briefcaseButton = view.findViewById<BottomNavigationItemView>(R.id.page_briefcase)
        val answersButton = view.findViewById<BottomNavigationItemView>(R.id.page_answer)

        briefcaseViewModel.briefCase.observe(this.viewLifecycleOwner, {

            showBriefcases(
                it,
                view,
                briefcaseButton,
                sharedPref,
                navController,
                answersButton,
                false
            )

            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.page_briefcase -> {
                        addNewBriefCaseButton.visibility = View.VISIBLE

                        showBriefcases(
                            it,
                            view,
                            briefcaseButton,
                            sharedPref,
                            navController,
                            answersButton,
                            false
                        )

                        true
                    }
                    R.id.page_answer -> {
                        addNewBriefCaseButton.visibility = View.INVISIBLE

                        showBriefcases(
                            it,
                            view,
                            briefcaseButton,
                            sharedPref,
                            navController,
                            answersButton,
                            true
                        )

                        true
                    }
                    else -> false
                }
            }
        })

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
        answersButton: BottomNavigationItemView,
        isAnswered: Boolean
    ) {
        val briefcaseAdapter = BriefCaseAdapter(it, isAnswered)
        val briefcaseRV = view.findViewById<RecyclerView>(R.id.rv_briefcase)
        briefcaseRV.layoutManager = LinearLayoutManager(context)
        briefcaseRV.adapter = briefcaseAdapter

        briefcaseAdapter.shortOnClickListener =
            object : BriefCaseAdapter.ShortOnClickListener {

                override fun ShortClick(briefCase: BriefcasesAndQuestions) {

                    if (briefcaseButton.isSelected) {
                        sharedPref?.edit()
                            ?.putLong(Constants.CURRENT_BRIEFCASE, briefCase.briefCaseId)
                            ?.apply()
                        navController.navigate(R.id.action_briefCaseFragment_to_questionsNotesFragment)
                    }
                    if (answersButton.isSelected) {
                        sharedPref?.edit()
                            ?.putLong(Constants.CURRENT_BRIEFCASE, briefCase.briefCaseId)
                            ?.apply()
                        navController.navigate(R.id.action_briefCaseFragment_to_answersFragment)
                    }
                }
            }
    }
}