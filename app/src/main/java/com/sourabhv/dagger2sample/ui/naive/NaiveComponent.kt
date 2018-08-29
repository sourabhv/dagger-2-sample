package com.sourabhv.dagger2sample.ui.naive

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Subcomponent
public interface NaiveComponent : AndroidInjector<NaiveActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NaiveActivity>()
}

@Module(subcomponents = [NaiveComponent::class])
abstract class NaiveModule {

    @Binds
    @IntoMap
    @ActivityKey(NaiveActivity::class)
    abstract fun bindFooActivityInjectorFactory(builder: NaiveComponent.Builder): AndroidInjector.Factory<out Activity>

}