package com.sourabhv.dagger2sample.ui

import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.ui.foobar.FooActivity
import com.sourabhv.dagger2sample.ui.multibindings.HitListActivity
import com.sourabhv.dagger2sample.ui.simple.SimpleActivity
import com.sourabhv.dagger2sample.ui.subcomponents.MakePlanetActivity
import com.sourabhv.dagger2sample.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.li_main.view.*

class MainActivity : AppCompatActivity() {

    enum class Option(val title: String, val desc: String) {
        Foo("Foo¬", """
            |¬ A simple preface of dagger2.10+
        """.trimMargin()),
        Simple("Simple¬", """
            |¬ Using dagger.android
            |¬ Creating SubComponents automatically using @ContributesAndroidInjection
        """.trimMargin()),
        SubComponents("SubComponents and Modules¬", """
            |¬ Showcases usage of modules in sub-components to inject dependencies between Activity and Fragment
            |¬ Scoped injection in sub-modules
        """.trimMargin()),
        MultiBindings("MultiBindings¬", """
            |¬ Showcases how multi-bindings work and how they are useful and it's limitations
            |¬ This includes @IntoMap, @IntoSet, support for Guava's Immutable Map/Set, and Kotlin
        """.trimMargin()),
//        CustomScope("TBD :: Custom Scope¬", """
//            |¬ Showcases custom scopes and how custom scope lifecycle is defined/controlled
//            |¬ This can be group of activities, a particular flow, something else
//        """.trimMargin()),
//        Gotchas("TBD :: Gotchas¬", """
//            |¬ Dagger lifesavers
//            |¬ Things that can start a War.
//        """.trimMargin()),
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

            init {
                itemView.setOnClickListener {
                    when(options[adapterPosition]) {
                        MainActivity.Option.Foo -> show(FooActivity::class)
                        MainActivity.Option.Simple -> show(SimpleActivity::class)
                        MainActivity.Option.SubComponents -> show(MakePlanetActivity::class)
                        MainActivity.Option.MultiBindings -> show(HitListActivity::class)
//                        MainActivity.Option.CustomScope -> Unit
//                        MainActivity.Option.Gotchas -> Unit
                    }
                }
            }

            fun bind(position: Int) {
                itemView.tvTitle.text = options[position].title
                itemView.tvBody.text = options[position].desc
            }
        }

    }

}
