package com.eftimoff.bakingapp.app.repositories;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

import io.reactivex.Single;

public interface RecipeRepository {

    Single<List<Recipe>> loadRecipes();

}
