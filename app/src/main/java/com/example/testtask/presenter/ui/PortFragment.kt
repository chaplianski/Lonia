package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.presenter.adapter.PortAdapter
import com.example.testtask.presenter.factories.PortViewModelFactory
import com.example.testtask.presenter.viewmodel.PortViewModel
import javax.inject.Inject


class PortFragment : Fragment() {


    @Inject
    lateinit var portViewModelFactory: PortViewModelFactory
    val portViewModel: PortViewModel by viewModels {portViewModelFactory}

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .portFragmentFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_port, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        portViewModel.getPortList()

        portViewModel.portList.observe(this.viewLifecycleOwner, { listPorts ->

            val portAdapter = PortAdapter(listPorts)
            val portRV = view.findViewById<RecyclerView>(R.id.rv_port)
            portRV.layoutManager = LinearLayoutManager(context)
            portRV.adapter = portAdapter

            portAdapter.shortOnClickListener = object : PortAdapter.ShortOnClickListener{
                override fun ShortClick(item: String) {

                    val builder = AlertDialog.Builder(context)
                    with(builder){
                        setTitle("Are your sure?")
                        setCancelable(true)
                        setMessage("You check port $item")
                        setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                            sharedPref?.edit()?.putString(Constants.CURRENT_PORT, item)?.apply()
                            val navController = Navigation.findNavController(view)
                            navController.navigate(R.id.action_portFragment_to_inspectionTypeFragment)
                        })
                        setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, i ->
                            dialog.cancel()
                        })

                    }
                    val alertDialog = builder.create()
                    alertDialog.show()
                }
            }

        })

    }


}