package es.hetfield.pruebatalentomobile.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.features.list.models.Center
import es.hetfield.pruebatalentomobile.setup.BaseFragment
import es.hetfield.pruebatalentomobile.setup.adapters.AdapterCellType
import es.hetfield.pruebatalentomobile.setup.adapters.BaseAdapter
import es.hetfield.pruebatalentomobile.setup.adapters.BaseModel
import es.hetfield.pruebatalentomobile.setup.network.ResponseResult
import es.hetfield.pruebatalentomobile.setup.utils.extensions.logDebug
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.launch


class ListFragment : BaseFragment(), BaseAdapter.OnItemListDelegate {

    private lateinit var adapter: BaseAdapter
    private var items: MutableList<BaseModel<Any>> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        recycler?.layoutManager = linearLayoutManager
        adapter = BaseAdapter(items, this)
        recycler?.adapter = adapter
        getHomeInfoRequest()

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
                    response.value.centers.forEach {
                        items.add(BaseModel(AdapterCellType.ADAPTER_TYPE_CENTER.typeCell, it))
                    }
                    adapter.notifyDataSetChanged()
                    recycler?.scheduleLayoutAnimation()
                }
                is ResponseResult.Error -> showError(response.message)
                else -> showError(getString(R.string.network_error))
            }
            hideProgressDialog()
        }
    }

    override fun onItemClicked(item: Any, position: Int, sender: Int?) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(item as Center))
    }

}