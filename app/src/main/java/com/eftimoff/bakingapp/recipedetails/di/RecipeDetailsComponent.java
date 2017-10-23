package com.eftimoff.bakingapp.recipedetails.di;

import com.eftimoff.bakingapp.app.injection.scopes.PerFragment;
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = RecipeDetailsModule.class)
public interface RecipeDetailsComponent {

    void inject(RecipeDetailsFragment recipeDetailsFragment);

}
