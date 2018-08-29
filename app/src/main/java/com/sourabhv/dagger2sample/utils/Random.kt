package com.sourabhv.dagger2sample.utils

import java.util.*

val random = Random()

fun randomInt(min: Int, max: Int) = random.nextInt(max - min + 1) + min

fun <T> List<T>.random() = this.shuffled()[0]

fun <T> List<T>.randomSubset(min: Int, max: Int) = this.shuffled().subList(0, randomInt(min, max))