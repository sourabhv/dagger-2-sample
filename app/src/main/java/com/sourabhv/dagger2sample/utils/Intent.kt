package com.sourabhv.dagger2sample.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import kotlin.reflect.KClass


fun Activity.show(activityClass: KClass<out Activity>) {
    startActivity(Intent(this, activityClass.java))
}

fun Fragment.show(activityClass: KClass<out Activity>) {
    startActivity(Intent(context, activityClass.java))
}

fun Context.show(activityClass: KClass<out Activity>) {
    startActivity(Intent(this, activityClass.java))
}
