package com.inspiringteam.ibm_airtouch.ui.transactions

import com.inspiringteam.ibm_airtouch.data.models.Transaction


interface TransactionsContract {
    interface View {
        fun showProducts(list: List<String>)

        fun showRelatedTransactions(list: List<Transaction>)

        fun showRelatedTransactionsSum(value: String)

        fun showError()
    }

    interface Presenter {
        fun getProducts()

        fun getRelatedTransactions(productName: String)

        fun getRelatedTransactionsSum(list: List<Transaction>)
    }
}