package com.example.lonia.presenter.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.presenter.adapter.InspectionTypeAdapter
import com.example.lonia.presenter.factories.InspectionTypeViewModelFactory
import com.example.lonia.presenter.ui.dialogs.SpecifyDialog
import com.example.lonia.presenter.viewmodel.InspectionTypeViewModel
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.presenter.adapter.VesselAdapter
import com.example.lonia.presenter.viewmodel.VesselsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class InspectionTypeFragment : Fragment() {

    @Inject
    lateinit var inspectionTypeViewModelFactory: InspectionTypeViewModelFactory
    val inspectionTypeViewModel: InspectionTypeViewModel by viewModels { inspectionTypeViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .inspectionTypeFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Inspection Type"
        return inflater.inflate(R.layout.fragment_inspection_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar =
            view.findViewById<ProgressBar>(R.id.pb_inspection_type_progressBar)
        val errorButton: Button = view.findViewById(R.id.bt_inspection_type_fragment_button)

        lifecycleScope.launchWhenResumed {
            inspectionTypeViewModel.getInspectionTypeList()
        }

     //   inspectionTypeViewModel.getInspectionTypeList()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                inspectionTypeViewModel.screenState.collect {
                    when (it) {
                        is InspectionTypeViewModel.InspectionTypeState.Loading ->
                            progressBar.visibility = View.VISIBLE
                        is InspectionTypeViewModel.InspectionTypeState.Success -> {
                            progressBar.visibility = View.INVISIBLE
                            val inspectionTypeAdapter = InspectionTypeAdapter(it.inspectionType)
                            val inspectionTypeRV = view.findViewById<RecyclerView>(R.id.rv_inspection_type)
                            inspectionTypeRV.layoutManager = LinearLayoutManager(context)
                            inspectionTypeRV.adapter = inspectionTypeAdapter

                            inspectionTypeAdapter.shortOnClickListener =
                                object : InspectionTypeAdapter.ShortOnClickListener {
                                    override fun ShortClick(item: String) {

                                        showDialog("inspection type", item)

                                    }
                                }
                            setupCustomDialog()
                        }
                        is InspectionTypeViewModel.InspectionTypeState.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            val inspectionTypeRV = view.findViewById<RecyclerView>(R.id.rv_inspection_type)
                            inspectionTypeRV.visibility = View.INVISIBLE
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



//        inspectionTypeViewModel.inspectionTypeList.observe(this.viewLifecycleOwner) {
//
//            val inspectionTypeAdapter = InspectionTypeAdapter(it)
//            val inspectionTypeRV = view.findViewById<RecyclerView>(R.id.rv_inspection_type)
//            inspectionTypeRV.layoutManager = LinearLayoutManager(context)
//            inspectionTypeRV.adapter = inspectionTypeAdapter
//
//            inspectionTypeAdapter.shortOnClickListener =
//                object : InspectionTypeAdapter.ShortOnClickListener {
//                    override fun ShortClick(item: String) {
//
//                        showDialog("inspection type", item)
//
//                    }
//                }
//            setupCustomDialog()
//        }
    }

    fun showDialog(nameItem: String, item: String) {
        SpecifyDialog.show(parentFragmentManager, nameItem, item)
    }

    fun setupCustomDialog() {
        SpecifyDialog.setupListener(parentFragmentManager, this) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            sharedPref?.edit()?.putString(Constants.CURRENT_INSPECTION_TYPE, it)?.apply()
            val navController = view?.let { Navigation.findNavController(it) }
            navController?.navigate(R.id.action_inspectionTypeFragment_to_inspectionSourceFragment)
        }
    }

    private fun getErrorMessage(@StringRes message: Int) {
        val messageTextView = view?.findViewById<TextView>(R.id.tv_inspection_type_fragment_error_message)
        messageTextView?.setText(message)
        messageTextView?.visibility = View.VISIBLE
        messageTextView?.setTextColor(Color.RED)
    }
}