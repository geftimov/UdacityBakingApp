package com.eftimoff.bakingapp.recipelist.di

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.getInt
import com.eftimoff.bakingapp.app.injection.ForApplication
import com.eftimoff.bakingapp.app.injection.PerFragment
import com.eftimoff.bakingapp.app.repositories.RecipeRepository
import com.eftimoff.bakingapp.recipelist.view.RecipeAdapter
import com.eftimoff.bakingapp.recipelist.view.RecipeAdapterImpl
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

    @Provides
    @PerFragment
    fun provideRecipeAdapter(recipeAdapter: RecipeAdapterImpl): RecipeAdapter = recipeAdapter

    @Provides
    @PerFragment
    fun provideLayoutManager(@ForApplication context: Context): RecyclerView.LayoutManager = GridLayoutManager(context, context.getInt(R.integer.recipe_list_column_size))

}