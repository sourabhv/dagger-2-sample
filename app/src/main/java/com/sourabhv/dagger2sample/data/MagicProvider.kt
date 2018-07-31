package com.sourabhv.dagger2sample.data

import javax.inject.Inject

class MagicProvider @Inject constructor() {

    fun getTheMagicWord() = "Dagger2!\n\nIt Lives!!!"

    fun getUserName() = "Mr. Stark"

}