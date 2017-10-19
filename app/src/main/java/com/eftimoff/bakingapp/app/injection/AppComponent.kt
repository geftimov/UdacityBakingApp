package com.eftimoff.bakingapp.app.injection

import com.eftimoff.bakingapp.app.images.GlideConfiguration
import com.eftimoff.bakingapp.recipedetails.di.RecipeDetailsComponent
import com.eftimoff.bakingapp.recipelist.di.RecipeDetailsModule
import com.eftimoff.bakingapp.recipelist.di.RecipeListComponent
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule
import com.eftimoff.bakingapp.widgets.RecipeInfoWidget
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetRemoteViewService
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun plus(module: RecipeListModule): RecipeListComponent

    fun plus(module: RecipeDetailsModule): RecipeDetailsComponent

    fun inject(glideConfiguration: GlideConfiguration)

    fun inject(recipeInfoWidget: RecipeInfoWidget)

    fun inject(recipeInfoWidgetManager: RecipeInfoWidgetManager)

    fun inject(recipeInfoWidgetRemoteViewService: RecipeInfoWidgetRemoteViewService)

}