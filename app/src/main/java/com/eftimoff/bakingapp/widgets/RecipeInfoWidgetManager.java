package com.eftimoff.bakingapp.widgets;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.BakingApplication;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.google.gson.Gson;

public class RecipeInfoWidgetManager {

    private static final String KEY_RECIPE = "Recipe";

    private BakingApplication application;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    public RecipeInfoWidgetManager(BakingApplication application, Gson gson, SharedPreferences sharedPreferences) {
        this.application = application;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void updateWidgetRecipe(Recipe recipe) {
        String recipeJson = gson.toJson(recipe);
        sharedPreferences.edit().putString(KEY_RECIPE, recipeJson).apply();
        updateWidget();
    }

    private void updateWidget() {
        Intent intent = new Intent(application, RecipeInfoWidget.class);

        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(application);
        ComponentName componentName = new ComponentName(application, RecipeInfoWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        application.sendBroadcast(intent);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
    }

    public Recipe getRecipe() {
        String recipe = sharedPreferences.getString(KEY_RECIPE, null);
        if (recipe == null) {
            return null;
        }

        return gson.fromJson(recipe, Recipe.class);

    }

}
