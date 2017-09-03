package com.eftimoff.bakingapp.app.extensions

import android.content.Context
import android.support.annotation.IntegerRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import com.eftimoff.bakingapp.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun FragmentManager.replace(fragment: Fragment) {
    beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
}

fun FragmentManager.add(fragment: Fragment) {
    beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
}

fun FragmentManager.replace(container: Int, fragment: Fragment) {
    beginTransaction()
            .replace(container, fragment)
            .commit()
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}

fun Context.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(this).inflate(layoutId, null)
}

fun Context.getInt(@IntegerRes intRes: Int): Int {
    return resources.getInteger(intRes)
}

fun Double.formatQuantity(n: Double): String {

    if (n.toString().substring(n.toString().indexOf(".") + 1).toInt() == 0) {
        return n.toInt().toString()
    }

    val denominator = (1 / Math.abs(n - n.toInt().toDouble())).toInt() //- 0.0001 so the division doesn't get affected by the float point aproximated representation
    val units = n.toInt()

    val numerator = units * denominator + 1
    if (numerator > units + 1) {
        return "$units 1/$denominator"
    }

    return "$numerator/$denominator"
}