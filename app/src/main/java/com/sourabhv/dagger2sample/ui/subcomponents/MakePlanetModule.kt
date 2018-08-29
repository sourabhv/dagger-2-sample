package com.sourabhv.dagger2sample.ui.subcomponents

import com.sourabhv.dagger2sample.di.ActivityScoped
import com.sourabhv.dagger2sample.di.CartID
import com.sourabhv.dagger2sample.di.FragmentScoped
import com.sourabhv.dagger2sample.utils.randomUUID
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MakePlanetModule {

    @FragmentScoped
    @ContributesAndroidInjector()
    abstract fun makePlanetFragment(): MakePlanetFragment

    @Module
    companion object {
        @CartID
        @Provides
        @JvmStatic
        @ActivityScoped
        fun provideCardID(activity: MakePlanetActivity): String {
            return activity.intent.getStringExtra(MakePlanetActivity.ARG_CARD_ID) ?: randomUUID()
        }
    }

}