package com.sourabhv.dagger2sample

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.li_main.view.*

class MainActivity : AppCompatActivity() {

    enum class Option(val title: String, val desc: String) {
        Simple("Simple¬", """
            |¬ Simple showcase of dagger 2.10+ with Android
            |¬ Showcases things like AndroidInjector, @ContributesAndroidInjection, and Custom Has_X_Injector
        """.trimMargin()),
        SubModules("SubModules¬", """
            |¬ Showcases support for submodules in dagger for custom injection scope
        """.trimMargin()),
        SubComponents("SubComponents¬", """
            |¬ TO-DO
        """.trimMargin()),
        CustomScope("Custom Scope¬", """
            |¬ Showcases custom scopes and how custom scope lifecycle is defined/controlled
            |¬ This can be group of activities, a particular flow, something else
        """.trimMargin()),
        MultiBindings("MultiBindings¬", """
            |¬ Showcases how multi-bindings work and how they are useful and it's limitations
            |¬ This includes @IntoMap, @IntoSet, support for Guava's Immutable Map/Set, and Kotlin
        """.trimMargin()),
        Goodies("Other awesome stuff¬", """
            |¬ Dagger lifesavers that people will thank you for.
        """.trimMargin()),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        options.layoutManager = LinearLayoutManager(this)
        options.setHasFixedSize(true)
        options.adapter = MainAdapter(Option.values().toList())
        options.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                with(resources.getDimension(R.dimen.card_offset).toInt()) {
                    outRect?.set(this, this, this, this)
                }
            }
        })
    }

    inner class MainAdapter(val options: List<Option>) : RecyclerView.Adapter<MainAdapter.ItemHolder>() {

        override fun getItemCount() = options.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = with(LayoutInflater.from(parent.context)) {
            ItemHolder(inflate(R.layout.li_main, parent, false))
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.bind(position)
        }

        inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(position: Int) {
                itemView.tvTitle.text = options[position].title
                itemView.tvBody.text = options[position].desc
            }
        }

    }

}
