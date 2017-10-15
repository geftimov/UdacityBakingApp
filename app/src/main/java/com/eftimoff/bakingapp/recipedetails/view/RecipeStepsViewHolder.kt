package com.eftimoff.bakingapp.recipedetails.view

import android.view.View
import com.eftimoff.bakingapp.app.models.Step
import com.eftimoff.bakingapp.app.view.BaseViewHolder
import kotlinx.android.synthetic.main.item_recipe_step.view.*

class RecipeStepsViewHolder(itemView: View, var callback: RecipeStepsAdapter.Callback?) : BaseViewHolder<Step>(itemView) {

    override fun bind(t: Step) {
        itemView.recipeStepTitle.text = t.shortDescription
        itemView.recipeStepNumber.text = (t.id + 1).toString()
    }

}