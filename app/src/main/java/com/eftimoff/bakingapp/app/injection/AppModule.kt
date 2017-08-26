package com.eftimoff.bakingapp.app.injection

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    @PerApplication
    @ForApplication
    fun provideApplicationContext(): Context = context

}