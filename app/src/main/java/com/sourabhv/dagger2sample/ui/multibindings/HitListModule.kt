package com.sourabhv.dagger2sample.ui.multibindings

import com.sourabhv.dagger2sample.di.ActivityScoped
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

interface HitListItem {
    fun name(): String
    fun bounty(): Float
}

@Module
class HitListModule {

    @Provides
    @ActivityScoped
    fun provideHitList(hitSet: Set<@JvmSuppressWildcards HitListItem>): List<@JvmSuppressWildcards HitListItem> {
        return hitSet.toList()
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideDon() = object : HitListItem {
        override fun name() = "DON"
        override fun bounty() = 1_000_000F
    }


    @Provides
    @IntoSet
    @ActivityScoped
    fun provideGabbar() = object : HitListItem {
        override fun name() = "Gabbar"
        override fun bounty() = 1000F
    }


    @Provides
    @IntoSet
    @ActivityScoped
    fun provideSaddam() = object : HitListItem {
        override fun name() = "Saddam Hussain"
        override fun bounty() = 10_000_000F
    }


    @Provides
    @IntoSet
    @ActivityScoped
    fun provideOsama() = object : HitListItem {
        override fun name() = "Osama Bin Laden"
        override fun bounty() = 0F
    }

}