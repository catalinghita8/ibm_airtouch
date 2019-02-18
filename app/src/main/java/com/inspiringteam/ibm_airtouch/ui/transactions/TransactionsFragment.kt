package com.inspiringteam.ibm_airtouch.ui.transactions


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.inspiringteam.ibm_airtouch.R
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import dagger.android.support.DaggerFragment
import javax.inject.Inject

@ActivityScoped
class TransactionsFragment @Inject constructor() : DaggerFragment(), TransactionsContract.View {
    @Inject
    lateinit var presenter: TransactionsPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onResume() {
        super.onResume()

        presenter.subscribe(this)
        presenter.getTransactions()
    }

    override fun showProducts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRelatedTransactions(productName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTransactions(transactions: List<Transaction>) {
        for(t in transactions){
            Log.d("FOUND TRANSACTION ", t.productName)
        }
    }

    override fun showError() {
       System.out.println("error ibm")
    }
}
