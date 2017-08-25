package com.eftimoff.bakingapp.app.repositories

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Single

interface RecipeRepository {

    fun loadRecipes(): Single<List<Recipe>>

}