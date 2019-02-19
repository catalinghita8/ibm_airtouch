package com.inspiringteam.ibm_airtouch.data

import com.inspiringteam.ibm_airtouch.data.scopes.Remote
import com.inspiringteam.ibm_airtouch.data.source.IbmRepository
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmRepositorySource
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmAPI
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmRemoteDataSource
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import dagger.Module
import dagger.Provides

@Module
class IbmDataModule {
    @Provides
    @AppScoped
    internal fun provideIbmRepository(remoteDataSource: IbmDataSource): IbmRepositorySource {
        return IbmRepository(remoteDataSource)
    }
}