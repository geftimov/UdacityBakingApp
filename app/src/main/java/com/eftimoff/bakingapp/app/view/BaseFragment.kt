package com.eftimoff.bakingapp.app.view

import android.arch.lifecycle.LifecycleFragment
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eftimoff.bakingapp.app.BakingApplication
import com.eftimoff.bakingapp.app.injection.AppComponent

abstract class BaseFragment : LifecycleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutRes(), container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        inject(BakingApplication.component)
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun inject(appComponent: AppComponent)

}