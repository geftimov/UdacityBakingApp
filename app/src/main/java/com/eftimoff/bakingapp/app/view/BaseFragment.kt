package com.eftimoff.bakingapp.app.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eftimoff.bakingapp.BakingApplication
import com.eftimoff.bakingapp.app.injection.AppComponent

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        inject(BakingApplication.component)
        return view
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun inject(appComponent: AppComponent)

}