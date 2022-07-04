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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lonia.R
import com.example.lonia.presenter.adapter.VesselAdapter
import com.example.lonia.presenter.factories.VesselViewModelFactory
import com.example.lonia.presenter.ui.dialogs.SpecifyDialog
import com.example.lonia.presenter.viewmodel.VesselsViewModel
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.presenter.adapter.QuestionnairesAdapter
import com.example.lonia.presenter.viewmodel.QuestionnairesViewModel
import kotlinx.coroutines.launch
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

        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Vessels"
        activitySupport.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activitySupport.supportActionBar?.setDisplayShowHomeEnabled(false)

        return inflater.inflate(R.layout.fragment_vessels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar =
            view.findViewById<ProgressBar>(R.id.pb_vessels_progressBar)
        val errorButton: Button = view.findViewById(R.id.bt_vessels_fragment_button)

        lifecycleScope.launchWhenResumed {
            vesselViewModel.getListVassels()
        }

//        vesselViewModel.getListVassels()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vesselViewModel.screenState.collect {
                    when (it) {
                        is VesselsViewModel.VesselState.Loading ->
                            progressBar.visibility = View.VISIBLE
//                        is VesselsViewModel.VesselState.DownWork ->
//                            navigateToNext()
                        is VesselsViewModel.VesselState.Success -> {
                            progressBar.visibility = View.INVISIBLE
                            val vasselAdapter = VesselAdapter(it.vessels)
                            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_vessels)
                            vesselsRV.layoutManager = LinearLayoutManager(context)
                            vesselsRV.adapter = vasselAdapter

                            vasselAdapter.shortOnClickListener = object : VesselAdapter.ShortOnClickListener {
                                override fun ShortClick(item: String) {

                                    showDialog("vessl", item)
                                }
                            }
                            setupCustomDialog()
                        }
                        is VesselsViewModel.VesselState.Error -> {
                            progressBar.visibility = View.INVISIBLE
                            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_vessels)
                            vesselsRV.visibility = View.INVISIBLE
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


//        vesselViewModel.vassels.observe(this.viewLifecycleOwner) { vesselsList ->
//
//            val vasselAdapter = VesselAdapter(vesselsList)
//            val vesselsRV: RecyclerView = view.findViewById(R.id.rv_vessels)
//            vesselsRV.layoutManager = LinearLayoutManager(context)
//            vesselsRV.adapter = vasselAdapter
//
//            vasselAdapter.shortOnClickListener = object : VesselAdapter.ShortOnClickListener {
//                override fun ShortClick(item: String) {
//
//                    showDialog("vessl", item)
//                }
//            }
//            setupCustomDialog()
//        }
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

    private fun getErrorMessage(@StringRes message: Int) {
        val messageTextView = view?.findViewById<TextView>(R.id.tv_vessels_fragment_error_message)
        messageTextView?.setText(message)
        messageTextView?.visibility = View.VISIBLE
        messageTextView?.setTextColor(Color.RED)
    }
}


