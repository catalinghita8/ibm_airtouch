package com.inspiringteam.ibm_airtouch.di

import android.app.Application
import android.content.Context
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import dagger.Binds
import dagger.Module
import dagger.Provides
import uk.co.transferx.app.util.schedulers.BaseSchedulerProvider
import uk.co.transferx.app.util.schedulers.SchedulerProvider
import javax.inject.Singleton

@Module
abstract class AppModule {
    //expose Application as an injectable context
    @Binds
    internal abstract fun bindContext(application: Application): Context

}