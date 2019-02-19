package com.inspiringteam.ibm_airtouch.data.source.remote

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import com.inspiringteam.ibm_airtouch.utils.Constants
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
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.API_HTTP)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @AppScoped
    internal fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @AppScoped
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @AppScoped
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @AppScoped
    internal fun provideOkhttpClient(cache: okhttp3.Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("Accept", "application/json")
             chain.proceed(requestBuilder.build())
        }
        return client.cache(cache).build()
    }
}
