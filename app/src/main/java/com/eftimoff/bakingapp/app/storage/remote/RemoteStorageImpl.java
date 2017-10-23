package com.eftimoff.bakingapp.app.storage.remote;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoteStorageImpl implements RemoteStorage {

    private RemoteApi remoteApi;

    @Inject
    public RemoteStorageImpl(RemoteApi remoteApi) {
        this.remoteApi = remoteApi;
    }

    @Override
    public Single<List<Recipe>> loadRecipes() {
        return remoteApi.loadRecipes();
    }
}
