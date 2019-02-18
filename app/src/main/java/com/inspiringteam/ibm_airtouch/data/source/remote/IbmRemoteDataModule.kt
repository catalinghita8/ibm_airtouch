package com.inspiringteam.ibm_airtouch.data.source.remote

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

@Module
class IbmRemoteDataModule {
    @AppScoped
    @Provides
    internal fun provideIbmApi(retrofit: Retrofit): IbmAPI {
        return retrofit.create(IbmAPI::class.java)
    }

    @Provides
    @AppScoped
    internal fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://gnb.dev.airtouchmedia.com")
                .build()
    }


    @Provides
    @AppScoped
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

}
