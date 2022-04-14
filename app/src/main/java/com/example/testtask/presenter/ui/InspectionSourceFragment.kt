package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.InspectionSourceAdapter
import com.example.testtask.presenter.factories.InspectionSourceViewModelFactory
import com.example.testtask.presenter.viewmodel.InspectionSourceViewModel
import javax.inject.Inject


class InspectionSourceFragment : Fragment() {

    @Inject
    lateinit var inspectionSourceViewModelFactory: InspectionSourceViewModelFactory
    val inspectionSourceViewModel: InspectionSourceViewModel by viewModels { inspectionSourceViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .inspectionSourceFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Inspection Source"
        return inflater.inflate(
            com.example.testtask.R.layout.fragment_inspection_source,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inspectionSourceViewModel.getInspectionSourceList()

        inspectionSourceViewModel.inspectionSourceList.observe(
            this.viewLifecycleOwner,
            { inspectionSource ->

                var inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
                val inspectionSourceRV =
                    view.findViewById<RecyclerView>(com.example.testtask.R.id.rv_inspection_source)
                inspectionSourceRV.layoutManager = LinearLayoutManager(context)
                inspectionSourceRV.adapter = inspectionSourceAdapter
                clickToItem(inspectionSourceAdapter)


                val searchView: SearchView =
                    view.findViewById(com.example.testtask.R.id.sv_inspection_source_search)
                var searchFilter = mutableListOf<String>()

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        if (!newText.isNullOrBlank()) {
                            searchFilter = inspectionSource.filter {
                                it.uppercase().contains(newText.toString().uppercase())
                            } as MutableList<String>
                        }
                        inspectionSourceAdapter = InspectionSourceAdapter(searchFilter)
                        inspectionSourceRV.adapter = inspectionSourceAdapter
                        clickToItem(inspectionSourceAdapter)

                        return false
                    }
                })

                val stopSearchButton = view.findViewById<ImageView>(R.id.search_close_btn)
                val textSearchField = view.findViewById<EditText>(R.id.search_src_text)
                stopSearchButton.setOnClickListener {
                    textSearchField.text.clear()
                    searchView.clearFocus()
                    inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
                    inspectionSourceRV.adapter = inspectionSourceAdapter
                    clickToItem(inspectionSourceAdapter)
                }
            })
    }

    fun clickToItem(inspectionSourceAdapter: InspectionSourceAdapter) {
        inspectionSourceAdapter.shortOnClickListener =
            object : InspectionSourceAdapter.ShortOnClickListener {
                override fun ShortClick(item: String) {

                    showDialog("inspection sourse", item)

                }
            }
        setupCustomDialog()
    }

    fun showDialog(nameItem: String, item: String) {
        SpecifyDialog.show(parentFragmentManager, nameItem, item)
    }

    fun setupCustomDialog() {
        SpecifyDialog.setupListener(parentFragmentManager, this) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit()?.putString(Constants.CURRENT_INSPECTION_SOURCE, it)?.apply()
            val navController = view?.let { Navigation.findNavController(it) }
            navController?.navigate(R.id.action_inspectionSourceFragment_to_inspectorNameFragment)
        }
    }
}