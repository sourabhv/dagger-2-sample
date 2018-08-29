package com.sourabhv.dagger2sample.ui.naive

import android.os.Bundle
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.data.MagicProvider
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_naive.*
import javax.inject.Inject

class NaiveActivity : DaggerAppCompatActivity() {

    @Inject lateinit var magicProvider: MagicProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naive)
        label.text = magicProvider.getTheMagicWord()
    }

}