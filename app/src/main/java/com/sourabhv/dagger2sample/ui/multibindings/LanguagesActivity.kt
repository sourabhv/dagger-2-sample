package com.sourabhv.dagger2sample.ui.multibindings

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sourabhv.dagger2sample.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.li_language.view.*
import javax.inject.Inject

class LanguagesActivity : DaggerAppCompatActivity() {

    @Inject lateinit var languages: List<@JvmSuppressWildcards Language>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        options.layoutManager = GridLayoutManager(this, 2)
        options.adapter = LanguagesAdapter(languages)
        options.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                with(resources.getDimension(R.dimen.card_offset).toInt()) {
                    outRect.set(this, this, this, this)
                }
            }
        })
    }


    inner class LanguagesAdapter(
            private val languages: List<Language>
    ) : RecyclerView.Adapter<LanguagesAdapter.LanguageHolder>() {

        override fun getItemCount() = languages.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder {
            val inflater = LayoutInflater.from(parent.context)
            return LanguageHolder(inflater.inflate(R.layout.li_language, parent, false))
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: LanguageHolder, position: Int) {
            holder.bind(position)
        }

        inner class LanguageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(position: Int) {
                val item = languages[position]
                itemView.name.text = item.name
                Glide.with(itemView.context)
                        .load(item.imageUrl)
                        .fitCenter()
                        .into(itemView.profileImage)
            }

        }

    }

}