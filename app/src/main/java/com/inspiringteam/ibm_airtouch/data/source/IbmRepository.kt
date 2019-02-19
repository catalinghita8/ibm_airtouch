package com.inspiringteam.ibm_airtouch.data.source

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.scopes.Remote
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmRepositorySource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@AppScoped
class IbmRepository @Inject constructor(@Remote val remoteDataSource: IbmDataSource) : IbmRepositorySource {
    lateinit var transactionList: ArrayList<Transaction>

    // Offline strategy can be implemented here through a local source
    override
    fun getProducts(): Single<List<String>> {
        return remoteDataSource.getProducts().flatMap { list ->
            // Cache intermediate result
            transactionList = ArrayList(list)
            Observable.fromIterable(list)
                .map { item -> item.productName }.toList()
        }
    }

    override
    fun getRelatedTransactions(productName: String): List<Transaction> {
        val list = ArrayList<Transaction>()
        for (t in transactionList) {
            if (t.productName.equals(productName)) list.add(t)
        }
        return list
    }

    override
    fun getRates(): Single<List<ExchangeRate>> {
        return remoteDataSource.getRates()
    }

}