package com.eftimoff.bakingapp.app.view

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

    var lifecycleRegistry : LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun getLifecycle(): LifecycleRegistry {
       return lifecycleRegistry
    }
}
