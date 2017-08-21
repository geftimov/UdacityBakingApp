package com.eftimoff.bakingapp.app.repositories

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor() : RecipeRepository {

    override fun loadRecipes(): Flowable<List<Recipe>> {
        return Flowable.just(listOf(Recipe("first"))).delay(20, TimeUnit.SECONDS)
    }
}