package com.inspiringteam.ibm_airtouch.data.source.remote

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScoped
class IbmRemoteDataSource @Inject constructor(val apiService: IbmAPI):
    IbmDataSource {

    override
    fun getProducts(): Single<List<Transaction>> {
        return apiService.getTransactions()
    }

    override
    fun getRates(): Single<List<ExchangeRate>> {
        return apiService.getRates()
    }

}