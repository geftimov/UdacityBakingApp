package com.eftimoff.bakingapp.recipelist.view

import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.app.models.Recipe

abstract class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    interface Callback {
        fun onRecipeClicked(recipe: Recipe)
    }

    abstract fun setRecipes(recipes: List<Recipe>)

    abstract fun setCallback(callback: Callback)

}