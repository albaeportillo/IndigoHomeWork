package com.indigoag.repository.network.bids

import com.indigoag.repository.util.capitalizeFirstWords
import com.indigoag.repository.util.formatAmount
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Bid(
    val id: String,
    val type: String,
    val price: String,
    val currency: String,
    val facility: Facility,
    val product: String,
    val lastUpdated: String,
    val deliveryStartDate: String,
    val location: Location,
) {
    data class Facility(
        val id: String,
        val name: String,
    )

    data class Location(
        val latitude: String,
        val longitude: String,
    )

    fun priceFormat(): String{
        return price.toDouble().formatAmount()
    }

    fun productCapitalize(): String {
        return product.capitalizeFirstWords()
    }
    fun typeCapitalize(): String {
        return type.capitalizeFirstWords()
    }

}
