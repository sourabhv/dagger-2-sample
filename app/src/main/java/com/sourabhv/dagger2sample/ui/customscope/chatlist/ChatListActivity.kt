package com.sourabhv.dagger2sample.ui.customscope.chatlist

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.ui.customscope.Chat
import com.sourabhv.dagger2sample.ui.customscope.ChatsRepository
import com.sourabhv.dagger2sample.ui.customscope.chatdetail.ChatDetailActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.li_chat.view.*
import javax.inject.Inject

class ChatListActivity : DaggerAppCompatActivity() {

    @Inject lateinit var chatsRepository: ChatsRepository
    private lateinit var adapter: ChatListAdapter
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        adapter = ChatListAdapter()
        chats.layoutManager = LinearLayoutManager(this)
        chats.setHasFixedSize(true)
        chats.adapter = adapter
        chats.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                with(resources.getDimension(R.dimen.card_offset).toInt()) {
                    outRect?.set(this, this, this, this)
                }
            }
        })
    }


    override fun onStart() {
        super.onStart()
        disposable = chatsRepository.getChats()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { adapter.chats = it },
                        onError = { it.printStackTrace() }
                )
    }


    inner class ChatListAdapter(chats: List<Chat> = listOf()) : RecyclerView.Adapter<ChatListAdapter.ChatHolder>() {

        var chats = chats
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount() = chats.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = with(LayoutInflater.from(parent.context)) {
            ChatHolder(inflate(R.layout.li_chat, parent, false))
        }

        override fun onBindViewHolder(holder: ChatHolder, position: Int) {
            holder.bind(position)
        }

        inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.setOnClickListener {
                    val chat = chats[adapterPosition]
                    ChatDetailActivity.makeIntent(itemView.context, chat.id)
                }
            }

            fun bind(position: Int) {
                val chat = chats[position]
                itemView.from.text = chat.members.joinToString(", ") { it.name }
                itemView.lastMessage.text = chat.messages.last().content
            }
        }

    }

}