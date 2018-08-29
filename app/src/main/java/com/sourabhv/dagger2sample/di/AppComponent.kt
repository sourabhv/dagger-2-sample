package com.sourabhv.dagger2sample.di

import android.app.Application
import com.sourabhv.dagger2sample.Needle
import com.sourabhv.dagger2sample.ui.customscope.ChatComponent
import com.sourabhv.dagger2sample.ui.naive.NaiveModule
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
    ActivityBindingModule::class,
    NaiveModule::class
])
interface AppComponent: AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    fun chatComponent(): ChatComponent.Builder

    fun inject(needle: Needle)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }

}