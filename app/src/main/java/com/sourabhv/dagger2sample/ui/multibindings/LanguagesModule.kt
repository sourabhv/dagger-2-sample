package com.sourabhv.dagger2sample.ui.multibindings

import com.sourabhv.dagger2sample.di.ActivityScoped
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

interface Language {
    val rank: Int
    val name: String
    val imageUrl: String
}

@Module
class HitListModule {

    @Provides
    @ActivityScoped
    fun provideLanguages(languages: Set<@JvmSuppressWildcards Language>): List<@JvmSuppressWildcards Language> {
        return languages.toList().sortedBy { it.rank }
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideSwift() = object : Language {
        override val name = "Swift"
        override val rank = 1
        override val imageUrl = "https://cdn.freebiesupply.com/logos/large/2x/swift-15-logo-png-transparent.png"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideKotlin() = object : Language {
        override val name = "Kotlin"
        override val rank = 2
        override val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1024px-Kotlin-logo.svg.png"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun providePython() = object : Language {
        override val name = "Python"
        override val rank = 3
        override val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Python-logo-notext.svg/2000px-Python-logo-notext.svg.png"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideGo() = object : Language {
        override val name = "Go"
        override val rank = 4
        override val imageUrl = "https://ih0.redbubble.net/image.520470450.9907/flat,550x550,075,f.u4.jpg"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideJavaScript() = object : Language {
        override val name = "JavaScript"
        override val rank = 5
        override val imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/480px-Unofficial_JavaScript_logo_2.svg.png"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideJava() = object : Language {
        override val name = "Java"
        override val rank = 6
        override val imageUrl = "http://diylogodesigns.com/blog/wp-content/uploads/2017/07/java-logo-vector-768x768.png"
    }


    @Provides
    @IntoSet
    @ActivityScoped
    fun provideC() = object : Language {
        override val name = "C"
        override val rank = 7
        override val imageUrl = "https://i.imgur.com/oVnXqGK.png"
    }

    @Provides
    @IntoSet
    @ActivityScoped
    fun provideHtmlCss() = object : Language {
        override val name = "HTML/CSS"
        override val rank = 8
        override val imageUrl = "https://cdn-images-1.medium.com/max/2000/1*Tgn8ZHDoG_ySzUp5ZPxa2w.jpeg"
    }

}