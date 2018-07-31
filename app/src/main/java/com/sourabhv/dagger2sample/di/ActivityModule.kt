package com.sourabhv.dagger2sample.di

import com.sourabhv.dagger2sample.ui.simple.SimpleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun simpleActivity(): SimpleActivity

}