package com.eftimoff.bakingapp.app.storage.remote

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Single
import javax.inject.Inject

class RemoteStorageImpl @Inject constructor(val remoteApi: RemoteApi) : RemoteStorage {

    override fun loadRecipes(): Single<List<Recipe>> {
        return remoteApi.loadRecipes()
    }

}