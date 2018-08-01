package com.sourabhv.dagger2sample.di

import com.sourabhv.dagger2sample.ui.multibindings.HitListActivity
import com.sourabhv.dagger2sample.ui.multibindings.HitListModule
import com.sourabhv.dagger2sample.ui.simple.SimpleActivity
import com.sourabhv.dagger2sample.ui.subcomponents.MakePlanetActivity
import com.sourabhv.dagger2sample.ui.subcomponents.MakePlanetModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun simpleActivity(): SimpleActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MakePlanetModule::class])
    abstract fun makePlanetActivity(): MakePlanetActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HitListModule::class])
    abstract fun hitListActivity(): HitListActivity

}

