package com.eftimoff.bakingapp.recipedetails.view

import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.models.Step

abstract class RecipeStepsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface Callback {
        fun onStepClicked(step: Step)
    }

    abstract fun setRecipe(recipe: Recipe)

    abstract fun setCallback(callback: Callback)

}