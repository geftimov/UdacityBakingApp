package com.eftimoff.bakingapp.app.repositories

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Flowable

interface RecipeRepository {

    fun loadRecipes(): Flowable<List<Recipe>>

}