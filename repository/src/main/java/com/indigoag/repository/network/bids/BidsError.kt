package com.indigoag.repository.network.bids

sealed class BidsError : Exception() {
    object TimeOut : BidsError()
    object UnAuthorized : BidsError()
}
