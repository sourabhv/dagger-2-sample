package com.sourabhv.dagger2sample.ui.foobar

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
public interface FooComponent : AndroidInjector<FooActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FooActivity>()
}