package es.hetfield.pruebatalentomobile.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.hetfield.pruebatalentomobile.R
import es.hetfield.pruebatalentomobile.features.list.models.Center
import es.hetfield.pruebatalentomobile.setup.BaseFragment
import es.hetfield.pruebatalentomobile.setup.utils.extensions.lazyUnsychronized
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {
    val center by lazyUnsychronized {
        arguments?.let { DetailFragmentArgs.fromBundle(it).Center }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        center?.let { fillData(it) }

    }

    private fun fillData(center: Center) {
        tvCenterName.text = center.title
        tvCenterLocation.text = getString(
            R.string.detail_address,
            center.address.streetAddress,
            center.address.locality,
            center.address.postalCode
        )
        tvDescriptionCourse.text = center.organization.description
        tvDescriptionCenter.text = center.organization.services
    }

}