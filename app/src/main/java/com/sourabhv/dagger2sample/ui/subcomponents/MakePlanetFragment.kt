package com.sourabhv.dagger2sample.ui.subcomponents

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import javax.inject.Inject

// This scope defines the lifecycle for Fragment
// Which is under @ActivityScoped
@ActivityScoped
class MakePlanetFragment @Inject constructor() : DaggerFragment() {

    @Inject @CartID lateinit var cartID: String
//    @Inject lateinit var makePlanetItemsAdapter: MakePlanetItemsAdapter
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
//        rvItems.adapter = makePlanetItemsAdapter
    }

    override fun onStart() {
        super.onStart()
        disposable = makePlanetRepository.getItems().subscribeBy(
//                onNext = { makePlanetItemsAdapter.items = it }
        )
    }

    override fun onStop() {
        disposable?.dispose()
        super.onStop()
    }

}