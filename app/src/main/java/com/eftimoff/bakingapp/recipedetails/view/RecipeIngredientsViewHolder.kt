package com.eftimoff.bakingapp.recipedetails.view

import android.view.View
import com.eftimoff.bakingapp.app.extensions.formatQuantity
import com.eftimoff.bakingapp.app.models.Ingredient
import com.eftimoff.bakingapp.app.view.BaseViewHolder
import kotlinx.android.synthetic.main.item_recipe_step_ingredients.view.*

class RecipeIngredientsViewHolder(itemView: View) : BaseViewHolder<List<Ingredient>>(itemView) {

    override fun bind(t: List<Ingredient>) {
//        itemView.recipeContainer.setOnClickListener {
//            callback?.onRecipeClicked(t)
//        }
//
        val builder: StringBuilder = StringBuilder()

        t.forEach {
            val quantity = it.quantity.formatQuantity(it.quantity)
            val measure = it.measure.toLowerCase()
            val ingredient = it.ingredient.toLowerCase()

            builder.append("$quantity $measure $ingredient\n")
        }
        itemView.recipeStepIngredientsList.text = builder.toString()
    }

}