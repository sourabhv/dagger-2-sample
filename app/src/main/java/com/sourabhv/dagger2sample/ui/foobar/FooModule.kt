package com.sourabhv.dagger2sample.ui.foobar

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


@Module(subcomponents = [FooComponent::class])
abstract class FooModule {

    @Binds
    @IntoMap
    @ActivityKey(FooActivity::class)
    abstract fun bindFooActivityInjectorFactory(builder: FooComponent.Builder): AndroidInjector.Factory<out Activity>

}