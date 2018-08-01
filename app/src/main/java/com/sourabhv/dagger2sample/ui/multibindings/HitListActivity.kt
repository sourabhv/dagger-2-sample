package com.sourabhv.dagger2sample.ui.multibindings

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sourabhv.dagger2sample.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HitListActivity : DaggerAppCompatActivity() {

    @Inject lateinit var hitList: List<@JvmSuppressWildcards HitListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        options.layoutManager = LinearLayoutManager(this)
        options.adapter = HitListAdapter(hitList)
    }


    inner class HitListAdapter(
            private val hitList: List<HitListItem>
    ) : RecyclerView.Adapter<HitListAdapter.HitListHolder>() {

        override fun getItemCount() = hitList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitListHolder {
            val inflater = LayoutInflater.from(parent.context)
            return HitListHolder(inflater.inflate(android.R.layout.simple_list_item_1, parent, false))
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: HitListHolder, position: Int) {
            val item = hitList[position]
            (holder.itemView as? TextView)?.text = "${item.name()} has a bounty of INR ${item.bounty()}"
        }

        inner class HitListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }

}