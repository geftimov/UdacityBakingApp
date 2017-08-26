package com.eftimoff.bakingapp

import android.app.Application
import android.content.Context
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.injection.AppModule
import com.eftimoff.bakingapp.app.injection.DaggerAppComponent

class BakingApplication : Application() {

    companion object {
        lateinit var component: AppComponent

        fun get(context: Context): AppComponent {
            return DaggerAppComponent.builder()
                    .appModule(AppModule(context))
                    .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = get(this)
    }


}