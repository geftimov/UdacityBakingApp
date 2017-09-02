package com.eftimoff.bakingapp.recipedetails.di

import com.eftimoff.bakingapp.app.injection.PerFragment
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsFragment
import com.eftimoff.bakingapp.recipelist.di.RecipeDetailsModule
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = arrayOf(RecipeDetailsModule::class))
interface RecipeDetailsComponent {

    fun inject(recipeDetailsFragment: RecipeDetailsFragment)

}