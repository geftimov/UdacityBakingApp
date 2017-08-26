package com.eftimoff.bakingapp.recipelist.view

import android.view.View
import com.bumptech.glide.Glide
import com.eftimoff.bakingapp.app.images.VideoThumbnailUrl
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseViewHolder
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeViewHolder(itemView: View) : BaseViewHolder<Recipe>(itemView) {

    override fun bind(t: Recipe) {
        itemView.recipeName.text = t.name
        itemView.recipeServings.text = t.servings.toString()
        if (t.image.isNotBlank()) {
            Glide.with(itemView).load(t.image).into(itemView.recipeImage)
        } else {
            val lastStepWithVideo = t.steps.lastOrNull { s -> !s.videoURL.isNullOrBlank() }

            lastStepWithVideo?.let { s ->
                Glide.with(itemView).load(VideoThumbnailUrl(s.videoURL)).into(itemView.recipeImage)
            }
        }
    }

}