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