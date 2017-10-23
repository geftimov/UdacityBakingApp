package com.eftimoff.bakingapp.app.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.eftimoff.bakingapp.app.BakingApplication;
import com.eftimoff.bakingapp.app.injection.qualifiers.ForApplication;
import com.eftimoff.bakingapp.app.injection.scopes.PerApplication;
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private BakingApplication application;

    public AppModule(BakingApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    @ForApplication
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @PerApplication
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @PerApplication
    public RecipeInfoWidgetManager provideRecipeInfoWidgetManager(Gson gson, SharedPreferences sharedPreferences) {
        return new RecipeInfoWidgetManager(application, gson, sharedPreferences);
    }

}
