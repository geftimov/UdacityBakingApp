package com.eftimoff.bakingapp.recipelist.view

import android.view.ViewGroup
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.inflate
import com.eftimoff.bakingapp.app.models.Recipe
import javax.inject.Inject

class RecipeAdapterImpl @Inject constructor() : RecipeAdapter() {

    private var recipes: List<Recipe> = arrayListOf()
    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = parent.context.inflate(R.layout.item_recipe)
        return RecipeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

}