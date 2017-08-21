package com.eftimoff.bakingapp.recipelist.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.eftimoff.bakingapp.app.repositories.RecipeRepository

/**
 * Factory for ViewModels
 */
@Suppress("UNCHECKED_CAST")
class RecipeListViewModelFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            return RecipeListViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}