package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.VesselAdapter
import com.example.testtask.presenter.factories.VesselViewModelFactory
import com.example.testtask.presenter.viewmodel.VesselsViewModel
import javax.inject.Inject


class VesselsFragment : Fragment() {

    @Inject
    lateinit var vesselViewModelFactory: VesselViewModelFactory
 //   val vesselViewModel: VasselsViewModel by viewModels {VesselViewModelFactory}



 //   private val vesselViewModel =

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
        return inflater.inflate(R.layout.fragment_vessels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val vesselViewModel = ViewModelProvider(this, vesselViewModelFactory).get(VesselsViewModel::class.java)

        vesselViewModel.getListVassels()

        vesselViewModel.vassels.observe(this.viewLifecycleOwner,{vesselsList ->

            val vasselAdapter = VesselAdapter(vesselsList)
            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_vessels)
            vesselsRV.layoutManager = LinearLayoutManager(activity)
            vesselsRV.adapter = vasselAdapter
        })



    }


}