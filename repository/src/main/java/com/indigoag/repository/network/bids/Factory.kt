package com.indigoag.repository.network.bids

import java.time.Instant
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

object Factory {

    private val bidTypes = setOf("basis", "cash")
    private val products = setOf("corn", "rice", "soybeans")
    private val names = setOf(
        "Clark" to "Kent",
        "Bruce" to "Wayne",
        "Diana" to "Prince",
        "Bruce" to "Banner",
        "Peter" to "Parker",
        "Wade" to "Wilson",
        "Lex" to "Luthor",
        "Billy" to "Batson",
        "Alan" to "Scott",
        "Steven" to "Rogers",
        "Scott" to "Summers",
        "Reed" to "Richards",
        "Kathy" to "Kane",
    )

    fun generateBid(): Bid =
        Bid(
            id = UUID.randomUUID().toString(),
            type = bidTypes.random(),
            price = Random.nextDouble(0.0, 200.0).toString(),
            currency = "USD",
            facility = Bid.Facility(
                id = UUID.randomUUID().toString(),
                name = names.random().run { "$first $second" }
            ),
            product = products.random(),
            lastUpdated = Instant.now().toString(),
            deliveryStartDate = LocalDate.now().plusDays(Random.nextLong(1, 50)).toString(),
            location = Bid.Location(
                latitude = Random.nextDouble(32.0, 40.0).toString(),
                longitude = Random.nextDouble(-90.0, -75.0).toString()
            )
        )
}