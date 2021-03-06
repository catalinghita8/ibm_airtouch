package com.inspiringteam.ibm_airtouch.di

import android.app.Application
import com.inspiringteam.ibm_airtouch.App
import com.inspiringteam.ibm_airtouch.data.IbmDataModule
import com.inspiringteam.ibm_airtouch.data.IbmRepositoryModule
import com.inspiringteam.ibm_airtouch.di.scopes.AppScoped
import com.inspiringteam.ibm_airtouch.utils.TestModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScoped
@Component(
    modules = [AppModule::class,
        IbmDataModule::class,
        IbmRepositoryModule::class,
        ActivityBindingModule::class,
        TestModule::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<App> {


    // We can now do DaggerAppComponent.builder().application(this).build().inject(this),
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}