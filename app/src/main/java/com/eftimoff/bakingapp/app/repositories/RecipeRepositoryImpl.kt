package com.eftimoff.bakingapp.app.repositories

import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorage
import io.reactivex.Single
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(val remoteStorage: RemoteStorage) : RecipeRepository {

    override fun loadRecipes(): Single<List<Recipe>> {
        return remoteStorage.loadRecipes()
    }

}