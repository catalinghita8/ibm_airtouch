package com.inspiringteam.ibm_airtouch.data

import com.inspiringteam.ibm_airtouch.data.scopes.Local
import com.inspiringteam.ibm_airtouch.data.scopes.Remote
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmAPI
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmRemoteDataModule
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmRemoteDataSource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import dagger.Module
import dagger.Provides

@Module(includes = [IbmRemoteDataModule::class])
class IbmRepositoryModule {
    @Provides
    @Remote
    @AppScoped
    internal fun provideIbmRemoteDataSource(apiService: IbmAPI): IbmDataSource {
        return IbmRemoteDataSource(apiService)
    }
}