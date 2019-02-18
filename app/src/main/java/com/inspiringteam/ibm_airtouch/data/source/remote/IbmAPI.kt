package com.inspiringteam.ibm_airtouch.data.source.remote

import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.models.TransactionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IbmAPI {
//    @Headers("Accept: application/json")
    @GET("transactions.json")
    fun getTransactions(): Single<List<Transaction>>
}