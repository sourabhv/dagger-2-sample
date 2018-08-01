package com.sourabhv.dagger2sample.ui.subcomponents

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.di.FragmentScoped
import kotlinx.android.synthetic.main.li_item.view.*
import javax.inject.Inject

@FragmentScoped
class MakePlanetItemsAdapter @Inject constructor(
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