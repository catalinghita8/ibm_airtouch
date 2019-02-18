package com.inspiringteam.ibm_airtouch.data.source.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IbmAPI {
    @GET("transactions")
    abstract fun getTransactions(): Single<>
}