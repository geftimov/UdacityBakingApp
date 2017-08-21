package com.eftimoff.bakingapp.recipelist.viewmodels

import android.arch.lifecycle.ViewModel
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.repositories.RecipeRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeListViewModel(private var recipeRepository: RecipeRepository) : ViewModel() {

    fun loadRecipes(): Flowable<List<Recipe>> {
        return recipeRepository.loadRecipes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}