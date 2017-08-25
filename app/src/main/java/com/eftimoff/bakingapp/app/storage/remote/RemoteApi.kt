package com.eftimoff.bakingapp.app.storage.remote

import com.eftimoff.bakingapp.app.models.Recipe
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteApi {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    fun loadRecipes(): Single<List<Recipe>>

}