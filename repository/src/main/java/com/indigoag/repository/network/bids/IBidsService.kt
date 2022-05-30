package com.indigoag.repository.network.bids

import kotlinx.coroutines.flow.Flow


interface IBidsService {
    fun streamingBids(): Flow<List<Bid>>
    fun streamingBid(id: String): Flow<Bid?>
}