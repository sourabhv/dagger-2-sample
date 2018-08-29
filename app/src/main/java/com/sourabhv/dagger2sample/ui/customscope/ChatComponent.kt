package com.sourabhv.dagger2sample.ui.customscope

import com.sourabhv.dagger2sample.di.ActivityScoped
import com.sourabhv.dagger2sample.ui.customscope.chatdetail.ChatDetailActivity
import com.sourabhv.dagger2sample.ui.customscope.chatlist.ChatListActivity
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ChatScope

@Subcomponent(modules = [ChatBindingModule::class, ChatModule::class])
@ChatScope
interface ChatComponent {

    @Subcomponent.Builder
    interface Builder {
        fun chatModule(chatModule: ChatModule): Builder
        fun build(): ChatComponent
    }

}

@Module
abstract class ChatBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun chatListActivity(): ChatListActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [])
    abstract fun chatDetailActivity(): ChatDetailActivity
}

@Module
class ChatModule {

    @Provides
    fun provideChatRepository() = ChatsRepository()

}