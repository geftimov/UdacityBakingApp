package com.eftimoff.bakingapp.recipelist.view

import android.view.View
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseViewHolder
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeViewHolder(itemView: View) : BaseViewHolder<Recipe>(itemView) {

    override fun bind(recipe: Recipe) {
        itemView.recipeName.text = recipe.name
        itemView.recipeServings.text = recipe.servings.toString()
    }

}