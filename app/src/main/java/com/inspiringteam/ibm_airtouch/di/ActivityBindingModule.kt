package com.inspiringteam.ibm_airtouch.di

import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import com.inspiringteam.ibm_airtouch.ui.transactions.TransactionsActivity
import com.inspiringteam.ibm_airtouch.ui.transactions.TransactionsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [TransactionsModule::class])
    abstract fun transactionsActivity(): TransactionsActivity

}