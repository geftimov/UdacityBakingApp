package com.eftimoff.bakingapp.app.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.eftimoff.bakingapp.app.BakingApplication
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: BakingApplication) {

    @Provides
    @PerApplication
    @ForApplication
    fun provideApplicationContext(): Context = application

    @Provides
    @PerApplication
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @PerApplication
    fun provideRecipeInfoWidgetManager(gson: Gson, sharedPreferences: SharedPreferences): RecipeInfoWidgetManager = RecipeInfoWidgetManager(application, gson, sharedPreferences)


}