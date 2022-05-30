package com.indigoag.android.homework.ui.home.bids


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.usecases.usecase.BidsUseCase
import com.indigoag.android.homework.ui.BaseViewModel
import com.indigoag.repository.network.bids.Bid
import org.koin.core.KoinComponent
import org.koin.core.inject


class HomeViewModel : BaseViewModel(), KoinComponent {
    private val bidsUseCase: BidsUseCase by inject()


    fun loadBids(): LiveData<List<Bid>> {
        return bidsUseCase.loadAllBids().asLiveData()
    }

    fun loadBidById(id: String) = bidsUseCase.loadBidById(id).asLiveData()

}