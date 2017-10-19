package com.eftimoff.bakingapp.widgets

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.BakingApplication
import com.eftimoff.bakingapp.app.models.Recipe
import com.google.gson.Gson


class RecipeInfoWidgetManager(private val application: BakingApplication, private val gson: Gson, private val sharedPreferences: SharedPreferences) {

    private val KEY_RECIPE = "Recipe"

    @SuppressLint("CommitPrefEdits")
    fun updateWidgetRecipe(recipe: Recipe) {
        val recipeJson = gson.toJson(recipe)

        sharedPreferences.edit().apply {
            putString(KEY_RECIPE, recipeJson)
            apply()
        }

        updateWidget()
    }

    private fun updateWidget() {
        val intent = Intent(application, RecipeInfoWidget::class.java)

        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val appWidgetManager = AppWidgetManager.getInstance(application)
        val componentName = ComponentName(application, RecipeInfoWidget::class.java)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)

        application.sendBroadcast(intent)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list)
    }

    fun getRecipe(): Recipe? {
        val recipe = sharedPreferences.getString(KEY_RECIPE, null)

        return recipe?.let { r -> gson.fromJson(r, Recipe::class.java) }
    }

}