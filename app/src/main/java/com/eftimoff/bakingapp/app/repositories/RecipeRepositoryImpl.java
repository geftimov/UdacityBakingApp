package com.eftimoff.bakingapp.app.repositories;

import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.storage.remote.RemoteStorage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecipeRepositoryImpl implements RecipeRepository {

    private RemoteStorage remoteStorage;

    @Inject
    public RecipeRepositoryImpl(RemoteStorage remoteStorage) {
        this.remoteStorage = remoteStorage;
    }

    @Override
    public Single<List<Recipe>> loadRecipes() {
        return remoteStorage.loadRecipes();
    }
}
