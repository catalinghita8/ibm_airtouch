package com.inspiringteam.ibm_airtouch.ui.transactions

import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import com.inspiringteam.ibm_airtouch.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TransactionsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun transactionsFragment(): TransactionsFragment

    @ActivityScoped
    @Binds
    abstract fun transactionsPresenter(presenter: TransactionsPresenter): TransactionsContract.Presenter
}