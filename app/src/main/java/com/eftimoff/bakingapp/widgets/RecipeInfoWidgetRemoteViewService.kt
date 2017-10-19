package com.eftimoff.bakingapp.widgets

import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.BakingApplication
import com.eftimoff.bakingapp.app.extensions.formatQuantity
import com.eftimoff.bakingapp.app.models.Recipe
import javax.inject.Inject


class RecipeInfoWidgetRemoteViewService : RemoteViewsService() {

    @Inject
    lateinit var recipeInfoWidgetManager: RecipeInfoWidgetManager

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return object : RemoteViewsFactory {
            private var recipe: Recipe? = null

            override fun onCreate() {
                BakingApplication.component.inject(this@RecipeInfoWidgetRemoteViewService)
            }

            override fun onDataSetChanged() {
                recipe = recipeInfoWidgetManager.getRecipe()
            }

            override fun onDestroy() {
            }

            override fun getCount(): Int {
                return recipe?.ingredients?.size ?: 0
            }

            override fun getViewAt(position: Int): RemoteViews? {
                val views = RemoteViews(packageName, R.layout.wigdet_info_recipe_ingredient)

                val ingredient = recipe?.ingredients?.get(position)

                val quantity = ingredient?.quantity?.formatQuantity(ingredient.quantity)
                val measure = ingredient?.measure?.toLowerCase()
                val ingredientText = ingredient?.ingredient?.toLowerCase()

                return ingredient?.let {
                    views.apply {
                        setTextViewText(R.id.recipe_ingredient_name, "$quantity $measure $ingredientText")
                    }
                }
            }

            override fun getLoadingView(): RemoteViews? {
                return null
            }

            override fun getViewTypeCount(): Int {
                return 1
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun hasStableIds(): Boolean {
                return false
            }
        }
    }
}