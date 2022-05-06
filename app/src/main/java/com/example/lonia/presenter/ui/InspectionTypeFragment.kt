package com.example.lonia.presenter.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.presenter.adapter.InspectionTypeAdapter
import com.example.lonia.presenter.factories.InspectionTypeViewModelFactory
import com.example.lonia.presenter.ui.dialogs.SpecifyDialog
import com.example.lonia.presenter.viewmodel.InspectionTypeViewModel
import com.example.lonia.di.DaggerAppComponent
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

        inspectionTypeViewModel.getInspectionTypeList()

        inspectionTypeViewModel.inspectionTypeList.observe(this.viewLifecycleOwner, {

            val inspectionTypeAdapter = InspectionTypeAdapter(it)
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
        })
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
}