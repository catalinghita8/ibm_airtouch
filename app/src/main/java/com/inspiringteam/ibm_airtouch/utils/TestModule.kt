package com.inspiringteam.ibm_airtouch.utils

import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import dagger.Module
import dagger.Provides
import uk.co.transferx.app.util.schedulers.BaseSchedulerProvider
import uk.co.transferx.app.util.schedulers.SchedulerProvider

@Module
class TestModule {
    @AppScoped
    @Provides
    internal fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider.instance
    }
}