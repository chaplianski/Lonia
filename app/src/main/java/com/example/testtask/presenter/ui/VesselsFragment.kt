package com.example.testtask.presenter.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.VesselAdapter
import com.example.testtask.presenter.factories.VesselViewModelFactory
import com.example.testtask.presenter.ui.SpecifyDialog.Companion.REQUEST_KEY
import com.example.testtask.presenter.viewmodel.VesselsViewModel
import javax.inject.Inject


class VesselsFragment : Fragment() {

    @Inject
    lateinit var vesselViewModelFactory: VesselViewModelFactory
    val vesselViewModel: VesselsViewModel by viewModels { vesselViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent
            .builder()
            .context(context)
            .build()
            .vesselsFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Vessels"
        return inflater.inflate(R.layout.fragment_vessels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vesselViewModel.getListVassels()

        vesselViewModel.vassels.observe(this.viewLifecycleOwner, { vesselsList ->

            val vasselAdapter = VesselAdapter(vesselsList)
            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_vessels)
            vesselsRV.layoutManager = LinearLayoutManager(context)
            vesselsRV.adapter = vasselAdapter

            vasselAdapter.shortOnClickListener = object : VesselAdapter.ShortOnClickListener {
                override fun ShortClick(item: String) {

                    showDialog("vessl", item)
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
            sharedPref?.edit()?.putString(Constants.CURRENT_VESSEL, it)?.apply()
            val navController = view?.let { Navigation.findNavController(it) }
            navController?.navigate(R.id.action_vesselsFragment_to_portFragment)
        }
    }
}


