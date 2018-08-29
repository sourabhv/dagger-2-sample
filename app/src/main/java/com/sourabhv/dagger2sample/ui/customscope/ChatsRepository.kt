package com.sourabhv.dagger2sample.ui.customscope

import com.sourabhv.dagger2sample.utils.random
import com.sourabhv.dagger2sample.utils.randomInt
import com.sourabhv.dagger2sample.utils.randomSubset
import com.sourabhv.dagger2sample.utils.randomUUID
import com.thedeanda.lorem.LoremIpsum
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class Chat(
        val id: String = randomUUID(),
        val members: List<User>,
        val messages: List<Message>
)

data class User(
        val id: String = randomUUID(),
        val name: String
)

data class Message(
        val id: String = randomUUID(),
        val content: String,
        val timestamp: Long,
        val from: User
)

@ChatScope
class ChatsRepository @Inject constructor() {

    private var chats: List<Chat> = makeRandomChats()
    private val chatsProcessor = BehaviorProcessor.create<List<Chat>>()

    init {
        chatsProcessor.onNext(chats)
    }

    fun getChats(): Flowable<List<Chat>> = chatsProcessor

    fun getChat(chatID: String): Flowable<Chat> = chatsProcessor
            .map { it.find { it.id == chatID } }

    fun sendMessage(chatID: String, content: String, from: User) {
        val message = Message(
                content = content,
                timestamp = System.currentTimeMillis(),
                from = from
        )
        val newChats = chats.map {
            if (it.id != chatID) it else it.copy(
                    messages = it.messages + message
            )
        }
        chatsProcessor.onNext(newChats)
    }

    private fun makeRandomChats(): List<Chat> {
        val tsDiff = TimeUnit.HOURS.toMillis(10)
        val lorem = LoremIpsum.getInstance()
        val users = (0..10).map { User(name = lorem.name) }
        return (0..5).map { _ ->
            val members = users.randomSubset(1, 3)
            val messages = (0..randomInt(10, 20)).map {
                Message(
                        content = lorem.getWords(3, 10),
                        timestamp = System.currentTimeMillis() - randomInt(0, tsDiff.toInt()),
                        from = members.random()
                )
            }.sortedBy { it.timestamp }.toMutableList()
            Chat(members = members, messages = messages)
        }
    }

}