package com.eftimoff.bakingapp.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.BakingApplication;
import com.eftimoff.bakingapp.app.models.Recipe;

import javax.inject.Inject;

public class RecipeInfoWidget extends AppWidgetProvider {

    @Inject
    RecipeInfoWidgetManager recipeInfoWidgetManager;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        BakingApplication.component().inject(this);

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeInfoWidgetManager);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, RecipeInfoWidgetManager recipeInfoWidgetManager) {
        Recipe recipe = recipeInfoWidgetManager.getRecipe();

        String widgetText = recipe != null ? recipe.getName() : context.getString(R.string.widget_no_recipe);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe_info);
        views.setTextViewText(R.id.widget_text, widgetText);
        views.setRemoteAdapter(R.id.widget_list, new Intent(context, RecipeInfoWidgetRemoteViewService.class));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

}
