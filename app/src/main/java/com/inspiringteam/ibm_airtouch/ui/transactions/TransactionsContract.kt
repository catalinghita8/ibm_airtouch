package com.inspiringteam.ibm_airtouch.ui.transactions

import com.inspiringteam.ibm_airtouch.data.models.Transaction


interface TransactionsContract {
    interface View {
//        fun showProducts(news: List<String>)

        fun showProducts(list: List<String>)
        fun showRelatedTransactions(productName: String)
        fun showTransactions(transactions: List<Transaction>)
        fun showError()
    }

    interface Presenter {

    }
}