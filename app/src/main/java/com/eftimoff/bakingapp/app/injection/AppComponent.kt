package com.eftimoff.bakingapp.app.injection

import com.eftimoff.bakingapp.app.images.GlideConfiguration
import com.eftimoff.bakingapp.recipelist.di.RecipeListComponent
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun plus(module: RecipeListModule): RecipeListComponent

    fun inject(glideConfiguration: GlideConfiguration)

}