package com.eftimoff.bakingapp.recipedetails.view

import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.app.models.Recipe

abstract class RecipeStepsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun setRecipe(recipe: Recipe)

}