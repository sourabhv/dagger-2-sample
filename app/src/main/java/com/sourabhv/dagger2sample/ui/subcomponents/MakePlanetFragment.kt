package com.sourabhv.dagger2sample.ui.subcomponents

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.di.ActivityScoped
import com.sourabhv.dagger2sample.di.CartID
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_make_planet.*
import kotlinx.android.synthetic.main.li_item.view.*
import javax.inject.Inject

// This scope defines the lifecycle for Fragment
// Which is under @ActivityScoped
@ActivityScoped
class MakePlanetFragment @Inject constructor() : DaggerFragment() {

    @Inject @CartID lateinit var cartID: String
    lateinit var makePlanetItemsAdapter: MakePlanetItemsAdapter
    @Inject lateinit var makePlanetRepository: MakePlanetRepository
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_make_planet, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvCartId.text = "Card: $cartID"
        rvItems.layoutManager = LinearLayoutManager(context)
        makePlanetItemsAdapter = MakePlanetItemsAdapter(makePlanetRepository)
        rvItems.adapter = makePlanetItemsAdapter
    }

    override fun onStart() {
        super.onStart()
        disposable = makePlanetRepository.getItems().subscribeBy(
                onNext = { makePlanetItemsAdapter.items = it }
        )
    }

    override fun onStop() {
        disposable?.dispose()
        super.onStop()
    }


    class MakePlanetItemsAdapter constructor(
            private val makePlanetRepository: MakePlanetRepository
    ) : RecyclerView.Adapter<MakePlanetItemsAdapter.ItemHolder>() {

        var items: List<Item> = listOf()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ItemHolder(inflater.inflate(R.layout.li_item, parent, false))
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.bind()
        }

        inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            init {
                itemView.tvAdd.setOnClickListener {
                    val item = items[adapterPosition]
                    makePlanetRepository.updateItem(item.copy(quantity = item.quantity + 1))
                }
            }

            @SuppressLint("SetTextI18n")
            fun bind() {
                val item = items[adapterPosition]
                itemView.tvName.text = "${item.quantity} x ${item.name}"
                itemView.tvAmount.text = item.amount.toString()
            }
        }

    }

}