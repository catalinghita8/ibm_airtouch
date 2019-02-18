package com.inspiringteam.ibm_airtouch.data.source

import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.scopes.Remote
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@AppScoped
class IbmRepository @Inject constructor(@Remote val remoteDataSource: IbmDataSource){
     lateinit var transactionList: ArrayList<Transaction>


    // Offline strategy can be implemented here through a local source
    fun getProducts(): Single<List<String>> {
        return remoteDataSource.getProducts().flatMap {
            list ->
            // Cache intermediate result
            transactionList =  ArrayList(list)
            Observable.fromIterable(list)
                    .map { item -> item.productName }.toList()
        }

    }



}