package es.hetfield.pruebatalentomobile.features.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.setup.BaseFragment
import es.hetfield.pruebatalentomobile.setup.network.ResponseResult
import es.hetfield.pruebatalentomobile.setup.utils.extensions.afterMeasure
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logDebug
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBTN.setOnClickListener { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToListFragment()) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.materialToolbar?.visibility = View.VISIBLE
    }

    private fun getHomeInfoRequest() {
        lifecycleScope.launch {
            showProgressDialog()
            when (val response =
                repository.getHomeInfo()) {
                is ResponseResult.Success -> {
                    logDebug("TODO OK ${response.value}")
                }
                is ResponseResult.Error -> showError(response.message)
                else -> showError(getString(R.string.network_error))
            }
            hideProgressDialog()
        }
    }
}