package com.sourabhv.dagger2sample.ui.subcomponents

import com.sourabhv.dagger2sample.di.ActivityScoped
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

data class Item(val name: String, val price: Long, val quantity: Int = 0) {
    val amount: Float get() = (price * quantity).toFloat() / 100
}

fun List<Item>.totalAmount() = this.map { it.quantity * it.price }.sum().toFloat() / 100

@ActivityScoped
class MakePlanetRepository @Inject constructor() {

    private var items = initialItems.map { it.copy() } // clone of items
    private val itemsProcessor = BehaviorProcessor.create<List<Item>>()

    init {
        itemsProcessor.onNext(items)
    }

    companion object {
        val initialItems = listOf(
                Item("Tree", 5000),
                Item("Land", 10000),
                Item("Jungle", 50000),
                Item("Lake", 80000),
                Item("Ocean", 412982),
                Item("Savannah", 99999),
                Item("Fish", 300),
                Item("Monkey", 998921),
                Item("Humans", 9921882),
                Item("Politicians", 1)
        )
    }

    fun getItems() = itemsProcessor

    fun updateItem(item: Item) {
        items = items.map { if (it.name == item.name) item else it }
        itemsProcessor.onNext(items)
    }

    fun getTotalAmount() = items.totalAmount()

}