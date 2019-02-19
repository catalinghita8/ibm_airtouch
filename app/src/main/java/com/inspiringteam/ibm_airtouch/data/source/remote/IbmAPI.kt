package com.inspiringteam.ibm_airtouch.data.source.remote

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IbmAPI {
    // TODO Fix interceptor
    @GET("transactions.json")
    fun getTransactions(): Single<List<Transaction>>

    @GET("rates.json")
    fun getRates(): Single<List<ExchangeRate>>
}