package com.eftimoff.bakingapp.recipedetails.view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.inflate
import com.eftimoff.bakingapp.app.models.Recipe
import kotlinx.android.synthetic.main.item_recipe_step.view.*
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

    private var selectedPosition = -1


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecipeIngredientsViewHolder) {
            holder.bind(recipe.ingredients)
        } else if (holder is RecipeStepsViewHolder) {
            val step = recipe.steps[position - 1]
            holder.bind(step)

            holder.itemView.setBackgroundColor(if (selectedPosition == position) Color.LTGRAY else Color.TRANSPARENT)

            holder.itemView.recipeStepContainer.setOnClickListener {
                if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedPosition)
                    selectedPosition = holder.getAdapterPosition()
                    notifyItemChanged(selectedPosition)
                    callback.onStepClicked(step)
                }
            }
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