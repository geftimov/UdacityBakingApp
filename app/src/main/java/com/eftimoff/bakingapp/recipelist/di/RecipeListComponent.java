package com.eftimoff.bakingapp.recipelist.di;

import com.eftimoff.bakingapp.app.injection.scopes.PerFragment;
import com.eftimoff.bakingapp.recipelist.view.RecipeListFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = RecipeListModule.class)
public interface RecipeListComponent {

    void inject(RecipeListFragment recipeListFragment);

}
