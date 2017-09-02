package com.eftimoff.bakingapp.recipelist.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.eftimoff.bakingapp.app.injection.ForApplication
import com.eftimoff.bakingapp.app.injection.PerFragment
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsFragment
import com.eftimoff.bakingapp.recipedetails.view.RecipeStepsAdapter
import com.eftimoff.bakingapp.recipedetails.view.RecipeStepsAdapterImpl
import dagger.Module
import dagger.Provides

@Module
class RecipeDetailsModule(var recipeDetailsFragment: RecipeDetailsFragment) {

    @Provides
    @PerFragment
    fun provideRecipeStepsAdapter(recipeStepsAdapter: RecipeStepsAdapterImpl): RecipeStepsAdapter = recipeStepsAdapter

    @Provides
    @PerFragment
    fun provideLayoutManager(@ForApplication context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)

}