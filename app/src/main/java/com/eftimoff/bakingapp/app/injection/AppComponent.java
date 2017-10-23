package com.eftimoff.bakingapp.app.injection;

import com.eftimoff.bakingapp.app.images.GlideConfiguration;
import com.eftimoff.bakingapp.app.injection.scopes.PerApplication;
import com.eftimoff.bakingapp.recipedetails.di.RecipeDetailsComponent;
import com.eftimoff.bakingapp.recipedetails.di.RecipeDetailsModule;
import com.eftimoff.bakingapp.recipelist.di.RecipeListComponent;
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule;
import com.eftimoff.bakingapp.widgets.RecipeInfoWidget;
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager;
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetRemoteViewService;

import dagger.Component;

@PerApplication
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    RecipeListComponent plus(RecipeListModule module);

    RecipeDetailsComponent plus(RecipeDetailsModule module);

    void inject(GlideConfiguration glideConfiguration);

    void inject(RecipeInfoWidget recipeInfoWidget);

    void inject(RecipeInfoWidgetManager recipeInfoWidgetManager);

    void inject(RecipeInfoWidgetRemoteViewService recipeInfoWidgetRemoteViewService);

}
