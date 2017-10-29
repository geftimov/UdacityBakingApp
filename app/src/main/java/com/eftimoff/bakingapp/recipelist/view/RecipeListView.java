package com.eftimoff.bakingapp.recipelist.view;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

public interface RecipeListView {

    void showRecipeList(List<Recipe> recipes);

    void showRecipeError();
    
}
