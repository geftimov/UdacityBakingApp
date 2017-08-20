package com.eftimoff.bakingapp.recipelist.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.eftimoff.bakingapp.app.injection.ForApplication
import com.eftimoff.bakingapp.app.injection.PerFragment
import dagger.Module
import dagger.Provides

@Module
class RecipeListModule {

    @Provides
    @PerFragment
    fun provideSharedPreferences(@ForApplication context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}