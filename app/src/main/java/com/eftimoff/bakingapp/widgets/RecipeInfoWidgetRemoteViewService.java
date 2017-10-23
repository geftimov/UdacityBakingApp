package com.eftimoff.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.BakingApplication;
import com.eftimoff.bakingapp.app.models.Ingredient;
import com.eftimoff.bakingapp.app.models.Recipe;

import javax.inject.Inject;

public class RecipeInfoWidgetRemoteViewService extends RemoteViewsService {

    @Inject
    RecipeInfoWidgetManager recipeInfoWidgetManager;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            private Recipe recipe;

            @Override
            public void onCreate() {
                BakingApplication.component().inject(RecipeInfoWidgetRemoteViewService.this);
            }

            @Override
            public void onDataSetChanged() {
                recipe = recipeInfoWidgetManager.getRecipe();
            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                return recipe == null ? 0 : recipe.getIngredients().size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.wigdet_info_recipe_ingredient);

                Ingredient ingredient = recipe.getIngredients().get(position);

                views.setTextViewText(R.id.recipe_ingredient_name, ingredient.toString());

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }
        };
    }

}
