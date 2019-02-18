package com.inspiringteam.ibm_airtouch.data.source.contracts

import com.inspiringteam.ibm_airtouch.data.models.Transaction
import io.reactivex.Single

interface IbmDataSource {
    fun getTransactions(): Single<List<Transaction>>
}