package com.eftimoff.bakingapp.app.storage.remote

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Single

interface RemoteStorage {

    fun loadRecipes(): Single<List<Recipe>>

}