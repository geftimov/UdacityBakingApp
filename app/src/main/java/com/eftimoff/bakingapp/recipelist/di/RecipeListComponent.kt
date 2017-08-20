package com.eftimoff.bakingapp.recipelist.di

import com.eftimoff.bakingapp.app.injection.PerFragment
import com.eftimoff.bakingapp.recipelist.view.RecipeListFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = arrayOf(RecipeListModule::class))
interface RecipeListComponent {

    fun inject(recipeListFragment: RecipeListFragment)

}