package com.sourabhv.dagger2sample.di

import android.app.Application
import com.sourabhv.dagger2sample.Needle
import com.sourabhv.dagger2sample.ui.foobar.FooModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    FooModule::class
])
interface AppComponent: AndroidInjector<DaggerApplication> {

    fun inject(needle: Needle)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }

}