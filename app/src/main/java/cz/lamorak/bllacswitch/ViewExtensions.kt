package cz.lamorak.bllacswitch

import android.content.Context
import android.view.View

fun Context.dip(value: Float): Int {
    return (value * resources.displayMetrics.density).toInt()
}

fun View.dip(value: Int) = context.dip(value.toFloat())

fun View.dip(value: Float) = context.dip(value)