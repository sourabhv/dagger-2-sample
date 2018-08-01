package com.sourabhv.dagger2sample.ui.subcomponents

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.di.CartID
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_make_planet.*
import javax.inject.Inject

class MakePlanetActivity : DaggerAppCompatActivity() {

    @Inject @CartID lateinit var cartID: String
    @Inject lateinit var makePlanetRepository: MakePlanetRepository
    @Inject lateinit var makePlanetFragmentProvider: Lazy<MakePlanetFragment>
    private var disposable: Disposable? = null

    companion object {
        const val ARG_CARD_ID = "arg_card_id"
        fun newIntent(context: Context, cartID: String) = Intent(context, MakePlanetActivity::class.java).apply {
            putExtra(ARG_CARD_ID, cartID)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_planet)
        tvCart.text = "Card: $cartID"
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, makePlanetFragmentProvider.get())
                    .commit()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        disposable = makePlanetRepository.getItems().subscribeBy(
                onNext = { tvAmount.text = "Amount: ₹${it.totalAmount()}"  },
                onError = { Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG).show() }
        )
    }

    override fun onStop() {
        disposable?.dispose()
        disposable = null
        super.onStop()
    }

}