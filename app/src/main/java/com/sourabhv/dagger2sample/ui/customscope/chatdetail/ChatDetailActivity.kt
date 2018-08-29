package com.sourabhv.dagger2sample.ui.customscope.chatdetail

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.ui.customscope.ChatsRepository
import com.sourabhv.dagger2sample.ui.customscope.Message
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_chat_detail.*
import kotlinx.android.synthetic.main.li_message.view.*
import javax.inject.Inject

class ChatDetailActivity : DaggerAppCompatActivity() {

    companion object {
        private const val ARG_CHAT_ID = "arg_chat_id"
        fun makeIntent(context: Context, chatID: String) = Intent(context, ChatDetailActivity::class.java).apply {
            putExtra(ARG_CHAT_ID, chatID)
        }
    }

    @Inject lateinit var chatsRepository: ChatsRepository
    private lateinit var adapter: MessagesAdapter
    private lateinit var chatID: String
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)
        chatID = intent.getStringExtra(ARG_CHAT_ID)
        adapter = MessagesAdapter()
        messages.layoutManager = LinearLayoutManager(this)
        messages.setHasFixedSize(true)
        messages.adapter = adapter
        messages.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                with(resources.getDimension(R.dimen.card_offset).toInt()) {
                    outRect?.set(this, this, this, this)
                }
            }
        })
    }


    override fun onStart() {
        super.onStart()
        disposable = chatsRepository.getChat(chatID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { adapter.messages = it.messages },
                        onError = { it.printStackTrace() }
                )
    }

    override fun onStop() {
        disposable?.dispose()
        super.onStop()
    }

    inner class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ChatHolder>() {

        var messages: List<Message> = listOf()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount() = messages.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = with(LayoutInflater.from(parent.context)) {
            ChatHolder(inflate(R.layout.li_message, parent, false))
        }

        override fun onBindViewHolder(holder: ChatHolder, position: Int) {
            holder.bind(position)
        }

        inner class ChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(position: Int) {
                val message = messages[position]
                itemView.from.text = message.from.name
                itemView.message.text = message.content
            }
        }

    }

}