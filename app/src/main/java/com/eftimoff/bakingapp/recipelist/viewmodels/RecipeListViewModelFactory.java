package com.eftimoff.bakingapp.recipelist.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.eftimoff.bakingapp.app.repositories.RecipeRepository;

public class RecipeListViewModelFactory implements ViewModelProvider.Factory {

    private RecipeRepository recipeRepository;

    public RecipeListViewModelFactory(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecipeListViewModel.class)) {
            return (T) new RecipeListViewModel(recipeRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
