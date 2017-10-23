package com.eftimoff.bakingapp.app.storage.remote;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface RemoteStorage {

    Single<List<Recipe>> loadRecipes();

}
