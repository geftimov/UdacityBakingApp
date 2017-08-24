package com.eftimoff.bakingapp.recipelist.di

import android.arch.lifecycle.ViewModelProviders
import com.eftimoff.bakingapp.app.injection.PerFragment
import com.eftimoff.bakingapp.app.repositories.RecipeRepository
import com.eftimoff.bakingapp.recipelist.view.RecipeListFragment
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModel
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RecipeListModule(var recipeListFragment: RecipeListFragment) {

    @Provides
    @PerFragment
    fun provideRecipeListViewModelFactory(recipeRepository: RecipeRepository): RecipeListViewModelFactory {
        return RecipeListViewModelFactory(recipeRepository)
    }

    @Provides
    @PerFragment
    fun provideRecipeListViewModel(recipeListViewModelFactory: RecipeListViewModelFactory): RecipeListViewModel {
        return ViewModelProviders.of(recipeListFragment, recipeListViewModelFactory).get(RecipeListViewModel::class.java)
    }

}