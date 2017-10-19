package com.eftimoff.bakingapp.app

import android.app.Application
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.injection.AppModule
import com.eftimoff.bakingapp.app.injection.DaggerAppComponent

class BakingApplication : Application() {

    companion object {
        lateinit var component: AppComponent

        fun get(application: BakingApplication): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(application))
                    .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = get(this)
    }

}