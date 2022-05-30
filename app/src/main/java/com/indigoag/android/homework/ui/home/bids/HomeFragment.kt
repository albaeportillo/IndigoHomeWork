package com.indigoag.android.homework.ui.home.bids

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.indigoag.android.homework.R
import com.indigoag.android.homework.component.ProgressFull
import com.indigoag.android.homework.databinding.HomeFragmentBinding
import com.indigoag.android.homework.ui.home.bids.list.HomeListAdapter
import com.indigoag.repository.network.bids.Bid
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var homeFragmentBinding: HomeFragmentBinding
    private val homeListAdapter: HomeListAdapter by lazy {
        HomeListAdapter {
            goToDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        setUpView()
        return homeFragmentBinding.root
    }

    private fun observeLiveData() {
        homeViewModel.loadBids().observe(viewLifecycleOwner) {
            ProgressFull.hideProgressFull()
            homeListAdapter.bidList = it as List<Bid>

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setUpView() {
        ProgressFull.crossProgressFull(requireContext())
        homeFragmentBinding.homeListAdapter = homeListAdapter
        homeFragmentBinding.search.setOnClickListener {
            homeListAdapter.filter.filter(
                homeFragmentBinding.search.text
            )
        }
        homeFragmentBinding.btnClear.setOnClickListener {
            if (!homeFragmentBinding.search.equals("")) {
                homeFragmentBinding.search.setText("")
                homeListAdapter.stringFilter = ""
                homeListAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun goToDetail(item: Bid) {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(
                item.id
            )
        )
    }


}