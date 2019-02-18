package com.inspiringteam.ibm_airtouch.ui.transactions

import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import com.inspiringteam.ibm_airtouch.mvp.BasePresenter
import javax.inject.Inject

@ActivityScoped
class TransactionsPresenter @Inject constructor() : BasePresenter<TransactionsContract.View>(),
        TransactionsContract.Presenter {

}