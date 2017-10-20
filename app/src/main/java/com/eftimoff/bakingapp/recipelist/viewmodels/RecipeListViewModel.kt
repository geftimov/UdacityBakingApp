package com.eftimoff.bakingapp.recipelist.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.eftimoff.bakingapp.app.extensions.plusAssign
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.repositories.RecipeRepository
import com.eftimoff.bakingapp.app.view.RxAwareViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeListViewModel(private var recipeRepository: RecipeRepository) : RxAwareViewModel() {

    private var data: MutableLiveData<List<Recipe>>? = null

    fun getData(): LiveData<List<Recipe>> {
        if (data == null) {
            data = MutableLiveData()
            loadRecipes(data!!)
        }
        return data!!
    }

    fun loadRecipes(data: MutableLiveData<List<Recipe>>) {
        disposables += loadRecipesFromBackend()
                .subscribe({ t -> data.value = t }, { t -> println(t.localizedMessage) })
    }

    fun loadRecipesFromBackend(): Single<List<Recipe>> {
        return recipeRepository.loadRecipes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}