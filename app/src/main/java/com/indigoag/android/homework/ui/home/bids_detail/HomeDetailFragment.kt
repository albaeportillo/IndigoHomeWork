package com.indigoag.android.homework.ui.home.bids_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.indigoag.android.homework.R
import com.indigoag.android.homework.databinding.HomeDetailFragmentBinding
import com.indigoag.android.homework.ui.home.bids.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeDetailFragment : Fragment(), OnMapReadyCallback {

    private val homeViewModel: HomeViewModel by viewModel()
    private val args by navArgs<HomeDetailFragmentArgs>()
    private lateinit var homeDetailFragmentBinding: HomeDetailFragmentBinding
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeDetailFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_detail_fragment, container, false)
        homeDetailFragmentBinding.lifecycleOwner = viewLifecycleOwner
        setUpView()
        return homeDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeLiveData() {
        homeViewModel.loadBidById(args.idBids).observe(viewLifecycleOwner) {
            it?.let { bid ->
                homeDetailFragmentBinding.bid = bid
                if (::map.isInitialized) {
                    createMarker(
                        bid.location.latitude,
                        bid.location.longitude,
                        bid.productCapitalize()
                    )
                }
            }
        }
    }

    private fun setUpView() {
        homeDetailFragmentBinding.viewModel = homeViewModel
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment: SupportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun createMarker(latitude: String, longitude: String, title: String) {
        val locateBid = LatLng(latitude.toDouble(), longitude.toDouble())
        val marker: Marker? = map.addMarker(MarkerOptions().position(locateBid).title(title))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(locateBid, 14f),
            4000,
            null
        )
        marker?.showInfoWindow()
    }


}