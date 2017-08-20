package com.eftimoff.bakingapp.app.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.eftimoff.bakingapp.R

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
