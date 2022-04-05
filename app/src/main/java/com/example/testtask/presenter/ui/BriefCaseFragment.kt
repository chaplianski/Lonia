package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.presenter.adapter.BriefCaseAdapter
import com.example.testtask.presenter.adapter.PortAdapter
import com.example.testtask.presenter.factories.BriefcaseViewModelFactory
import com.example.testtask.presenter.viewmodel.BriefCaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject


class BriefCaseFragment : Fragment() {

    @Inject
    lateinit var briefcaseViewModelFactory: BriefcaseViewModelFactory
    val briefcaseViewModel: BriefCaseViewModel by viewModels {briefcaseViewModelFactory}

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_briefcase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        val addNewBriefCaseButton: FloatingActionButton = view.findViewById(R.id.bt_briefcase_add)

        briefcaseViewModel.getBriefCaseList()

        briefcaseViewModel.briefCase.observe(this.viewLifecycleOwner, {

        //    Log.d("MyLog", "it : $it")
            if (!it.isEmpty()){
                val briefcaseAdapter = BriefCaseAdapter(it)
                val briefcaseRV = view.findViewById<RecyclerView>(R.id.rv_briefcase)
                briefcaseRV.layoutManager = LinearLayoutManager(context)
                briefcaseRV.adapter = briefcaseAdapter

                briefcaseAdapter.shortOnClickListener = object : BriefCaseAdapter.ShortOnClickListener{

                    override fun ShortClick(briefCase: BriefCase) {
                        navController.navigate(R.id.action_briefCaseFragment_to_questionsFragment)
                    }
                }
            }


        })


        addNewBriefCaseButton.setOnClickListener {

            navController.navigate(R.id.action_briefCaseFragment_to_vesselsFragment)
        }

        val bottomNavigation: BottomNavigationView = view.findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_briefcase -> {
                    Log.d("MyLog", "Click Briefcase")
                    true
                }
                R.id.page_answer -> {
                    navController.navigate(R.id.action_briefCaseFragment_to_answersFragment)
                    Log.d("MyLog", "Click Answer")
                    true
                }
                else -> false
            }
        }

    }


}