package com.sourabhv.dagger2sample.ui.simple

import android.annotation.SuppressLint
import android.os.Bundle
import com.sourabhv.dagger2sample.R
import com.sourabhv.dagger2sample.data.MagicProvider
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_foo.*
import javax.inject.Inject

class SimpleActivity : DaggerAppCompatActivity() {

    @Inject lateinit var magicProvider: MagicProvider

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foo)
        label.text = "Welcome, ${magicProvider.getUserName()}."

    }

}