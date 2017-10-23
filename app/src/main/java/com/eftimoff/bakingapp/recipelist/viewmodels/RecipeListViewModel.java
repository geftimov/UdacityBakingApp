package com.eftimoff.bakingapp.recipelist.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.repositories.RecipeRepository;
import com.eftimoff.bakingapp.app.view.RxAwareViewModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeListViewModel extends RxAwareViewModel {

    private RecipeRepository recipeRepository;

    private MutableLiveData<List<Recipe>> data;

    public RecipeListViewModel(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public LiveData<List<Recipe>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            loadRecipes(data);
        }
        return data;
    }

    private void loadRecipes(MutableLiveData<List<Recipe>> data) {
        compositeDisposable.add(loadRecipesFromBackend()
                .subscribe(data::setValue,
                        throwable -> Log.w("RecipeListViewModel", throwable.getMessage())));
    }

    private Single<List<Recipe>> loadRecipesFromBackend() {
        return recipeRepository.loadRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
