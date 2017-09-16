package com.eftimoff.bakingapp.recipedetails.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.inflate
import com.eftimoff.bakingapp.app.models.Recipe
import javax.inject.Inject

class RecipeStepsAdapterImpl @Inject constructor() : RecipeStepsAdapter() {

    private lateinit var recipe: Recipe
    private lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.context.inflate(viewType)
        when (viewType) {
            R.layout.item_recipe_step_ingredients -> return RecipeIngredientsViewHolder(view)
            R.layout.item_recipe_step -> return RecipeStepsViewHolder(view, callback)
        }
        return RecipeStepsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecipeIngredientsViewHolder) {
            holder.bind(recipe.ingredients)
        } else if (holder is RecipeStepsViewHolder) {
            holder.bind(recipe.steps[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return R.layout.item_recipe_step_ingredients
        }
        return R.layout.item_recipe_step
    }

    override fun getItemCount(): Int {
        return recipe.steps.size + 1
    }

    override fun setRecipe(recipe: Recipe) {
        this.recipe = recipe
        notifyDataSetChanged()
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

}