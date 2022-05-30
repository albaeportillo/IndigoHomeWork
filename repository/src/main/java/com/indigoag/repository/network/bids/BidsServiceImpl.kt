package com.indigoag.repository.network.bids

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import java.time.Instant
import kotlin.random.Random

class BidsServiceImpl : IBidsService {

    @Volatile
    private var latestBuilds = List(20) { Factory.generateBid() }

    override fun streamingBids(): Flow<List<Bid>> = flow {
        while (currentCoroutineContext().isActive) {
            delay(5_000)
            latestBuilds = latestBuilds.map { it.updatePrice() }
            emit(latestBuilds)
        }
    }

    override fun streamingBid(id: String): Flow<Bid?> = flow {
        while (currentCoroutineContext().isActive) {
            latestBuilds.firstOrNull { it.id == id }?.let {
                emit(it)
            } ?: throw Exception("Bid with id: `$id` not found")
            delay(5_000)
        }
    }

    private fun Bid.updatePrice(): Bid = copy(
        price = Random.nextDouble(0.0, 200.0).toString(),
        lastUpdated = Instant.now().toString()
    )

}