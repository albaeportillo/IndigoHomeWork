package com.example.usecases.usecase

import com.indigoag.repository.network.bids.Bid
import com.indigoag.repository.network.bids.IBidsService
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class BidsUseCase : KoinComponent {

    private val iBidsService: IBidsService by inject()

    fun loadAllBids(): Flow<List<Bid>> {
        return iBidsService.streamingBids()
    }

    fun loadBidById(id: String): Flow<Bid?> {
        return iBidsService.streamingBid(id)
    }
}