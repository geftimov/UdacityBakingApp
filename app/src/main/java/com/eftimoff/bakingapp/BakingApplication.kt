package com.eftimoff.bakingapp

import android.app.Application
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.injection.AppModule
import com.eftimoff.bakingapp.app.injection.DaggerAppComponent

class BakingApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}