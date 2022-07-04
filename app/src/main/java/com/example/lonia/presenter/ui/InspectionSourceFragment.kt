package com.example.lonia.presenter.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.presenter.adapter.InspectionSourceAdapter
import com.example.lonia.presenter.adapter.VesselAdapter
import com.example.lonia.presenter.factories.InspectionSourceViewModelFactory
import com.example.lonia.presenter.ui.dialogs.SpecifyDialog
import com.example.lonia.presenter.viewmodel.InspectionSourceViewModel
import com.example.lonia.presenter.viewmodel.VesselsViewModel
import kotlinx.coroutines.launch

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
            R.layout.fragment_inspection_source,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar =
            view.findViewById<ProgressBar>(R.id.pb_inspection_source_progressBar)
        val errorButton: Button = view.findViewById(R.id.bt_inspection_source_fragment_button)
        val searchView: SearchView =
            view.findViewById(R.id.sv_inspection_source_search)

        lifecycleScope.launchWhenResumed {
            inspectionSourceViewModel.getInspectionSourceList()
        }

        //    inspectionSourceViewModel.getInspectionSourceList()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                inspectionSourceViewModel.screenState.collect {
                    when (it) {
                        is InspectionSourceViewModel.InspectionSourceState.Loading ->
                            progressBar.visibility = View.VISIBLE
//                        is VesselsViewModel.VesselState.DownWork ->
//                            navigateToNext()
                        is InspectionSourceViewModel.InspectionSourceState.Success -> {
                            val inspectionSource = it.inspectionSource
                            progressBar.visibility = View.INVISIBLE
                            var inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
                            val inspectionSourceRV =
                                view.findViewById<RecyclerView>(R.id.rv_inspection_source)
                            inspectionSourceRV.layoutManager = LinearLayoutManager(context)
                            inspectionSourceRV.adapter = inspectionSourceAdapter
                            clickToItem(inspectionSourceAdapter)


                            var searchFilter = mutableListOf<String>()

                            searchView.setOnQueryTextListener(object :
                                SearchView.OnQueryTextListener {

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

                            val stopSearchButton =
                                view.findViewById<ImageView>(R.id.search_close_btn)
                            val textSearchField = view.findViewById<EditText>(R.id.search_src_text)
                            stopSearchButton.setOnClickListener {
                                textSearchField.text.clear()
                                searchView.clearFocus()
                                inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
                                inspectionSourceRV.adapter = inspectionSourceAdapter
                                clickToItem(inspectionSourceAdapter)
                            }

                            setupCustomDialog()
                        }
                        is InspectionSourceViewModel.InspectionSourceState.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_inspection_source)
                            vesselsRV.visibility = View.INVISIBLE
                            searchView.visibility = View.INVISIBLE
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

//        inspectionSourceViewModel.inspectionSourceList.observe(
//            this.viewLifecycleOwner
//        ) { inspectionSource ->
//
//            var inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
//            val inspectionSourceRV =
//                view.findViewById<RecyclerView>(R.id.rv_inspection_source)
//            inspectionSourceRV.layoutManager = LinearLayoutManager(context)
//            inspectionSourceRV.adapter = inspectionSourceAdapter
//            clickToItem(inspectionSourceAdapter)
//
//
//            val searchView: SearchView =
//                view.findViewById(R.id.sv_inspection_source_search)
//            var searchFilter = mutableListOf<String>()
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    if (!newText.isNullOrBlank()) {
//                        searchFilter = inspectionSource.filter {
//                            it.uppercase().contains(newText.toString().uppercase())
//                        } as MutableList<String>
//                    }
//                    inspectionSourceAdapter = InspectionSourceAdapter(searchFilter)
//                    inspectionSourceRV.adapter = inspectionSourceAdapter
//                    clickToItem(inspectionSourceAdapter)
//
//                    return false
//                }
//            })
//
//            val stopSearchButton = view.findViewById<ImageView>(R.id.search_close_btn)
//            val textSearchField = view.findViewById<EditText>(R.id.search_src_text)
//            stopSearchButton.setOnClickListener {
//                textSearchField.text.clear()
//                searchView.clearFocus()
//                inspectionSourceAdapter = InspectionSourceAdapter(inspectionSource)
//                inspectionSourceRV.adapter = inspectionSourceAdapter
//                clickToItem(inspectionSourceAdapter)
//            }
//        }
//    }
//
//    fun clickToItem(inspectionSourceAdapter: InspectionSourceAdapter) {
//        inspectionSourceAdapter.shortOnClickListener =
//            object : InspectionSourceAdapter.ShortOnClickListener {
//                override fun ShortClick(item: String) {
//
//                    showDialog("inspection sourse", item)
//
//                }
//            }
//        setupCustomDialog()
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

    private fun getErrorMessage(@StringRes message: Int) {
        val messageTextView = view?.findViewById<TextView>(R.id.tv_inspection_source_fragment_error_message)
        messageTextView?.setText(message)
        messageTextView?.visibility = View.VISIBLE
        messageTextView?.setTextColor(Color.RED)
    }

    fun clickToItem(inspectionSourceAdapter: InspectionSourceAdapter) {
        inspectionSourceAdapter.shortOnClickListener =
            object : InspectionSourceAdapter.ShortOnClickListener {
                override fun ShortClick(item: String) {

                    showDialog("inspection sourse", item)
                }
            }
    }
}