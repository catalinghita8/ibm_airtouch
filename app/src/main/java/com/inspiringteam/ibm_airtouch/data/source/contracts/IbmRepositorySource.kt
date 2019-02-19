package com.inspiringteam.ibm_airtouch.data.source.contracts

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import io.reactivex.Single

interface IbmRepositorySource {
    // Offline strategy can be implemented here through a local source
    fun getProducts(): Single<List<String>>

    fun getRelatedTransactions(productName: String): List<Transaction>

    fun getRates(): Single<List<ExchangeRate>>
}