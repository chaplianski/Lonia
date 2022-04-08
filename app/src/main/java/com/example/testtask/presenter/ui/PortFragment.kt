package com.example.testtask.presenter.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
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
import com.example.testtask.presenter.adapter.PortAdapter
import com.example.testtask.presenter.factories.PortViewModelFactory
import com.example.testtask.presenter.viewmodel.PortViewModel
import javax.inject.Inject


class PortFragment : Fragment() {

    @Inject
    lateinit var portViewModelFactory: PortViewModelFactory
    val portViewModel: PortViewModel by viewModels { portViewModelFactory }


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
        activity?.title = "Ports"
        return inflater.inflate(R.layout.fragment_port, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.getPreferences(Context.MODE_PRIVATE)

        portViewModel.getPortList()

        portViewModel.portList.observe(this.viewLifecycleOwner, { listPorts ->

            var portAdapter = PortAdapter(listPorts)
            val portRV = view.findViewById<RecyclerView>(R.id.rv_port)
            portRV.layoutManager = LinearLayoutManager(context)
            portRV.adapter = portAdapter
            clickToItem(portAdapter)

            val searchView: SearchView = view.findViewById(R.id.sv_port_search)
            var searchFilter = mutableListOf<String>()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (!newText.isNullOrBlank()) {
                        searchFilter = listPorts.filter {
                            it.uppercase().contains(newText.toString().uppercase())
                        } as MutableList<String>
                    }
                    portAdapter = PortAdapter(searchFilter)
                    portRV.adapter = portAdapter
                    clickToItem(portAdapter)

                    return false
                }
            })

            val stopSearchButton = view.findViewById<ImageView>(R.id.search_close_btn)
            val textSearchField = view.findViewById<EditText>(R.id.search_src_text)
            stopSearchButton.setOnClickListener {
                textSearchField.text.clear()
                searchView.clearFocus()

                portAdapter = PortAdapter(listPorts)
                portRV.adapter = portAdapter
                clickToItem(portAdapter)
            }
        })

    }

    fun clickToItem(portAdapter: PortAdapter) {
        portAdapter.shortOnClickListener = object : PortAdapter.ShortOnClickListener {
            override fun ShortClick(item: String) {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val builder = AlertDialog.Builder(context)
                with(builder) {
                    setTitle("Are your sure?")
                    setCancelable(true)
                    setMessage("You check port $item")
                    setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, id ->
                        sharedPref?.edit()?.putString(Constants.CURRENT_PORT, item)?.apply()
                        val navController = view?.let { Navigation.findNavController(it) }
                        navController?.navigate(R.id.action_portFragment_to_inspectionTypeFragment)
                    })
                    setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, i ->
                        dialog.cancel()
                    })

                }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }
}