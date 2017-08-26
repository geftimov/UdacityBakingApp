package com.eftimoff.bakingapp.recipelist.view

import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.app.models.Recipe

abstract class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    abstract fun setRecipes(recipes: List<Recipe>)

}