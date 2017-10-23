package com.eftimoff.bakingapp.app.storage.remote;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RemoteApi {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Single<List<Recipe>> loadRecipes();

}
