package com.eftimoff.bakingapp.app.injection

import android.content.Context
import com.eftimoff.bakingapp.BakingApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: BakingApplication) {

    @Provides
    @PerApplication
    @ForApplication
    fun provideApplication(): BakingApplication = app

    @Provides
    @PerApplication
    @ForApplication
    fun provideApplicationContext(): Context = app

}