package com.inspiringteam.ibm_airtouch.data.source

import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.scopes.Remote
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import io.reactivex.Single
import javax.inject.Inject

@AppScoped
class IbmRepository @Inject constructor(@Remote val remoteDataSource: IbmDataSource): IbmDataSource {
    // Offline strategy can be implemented here through a local source
    override fun getTransactions(): Single<List<Transaction>> {
        return remoteDataSource.getTransactions()
    }
}