package com.sourabhv.dagger2sample

import com.sourabhv.dagger2sample.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class Needle : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .apply { inject(this@Needle) }

}