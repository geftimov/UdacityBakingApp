package com.eftimoff.bakingapp.recipelist.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModel
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModelFactory
import javax.inject.Inject


class RecipeListFragment : BaseFragment() {

    @Inject
    lateinit var recipeListViewModelFactory: RecipeListViewModelFactory

    lateinit var viewModel: RecipeListViewModel

    companion object {
        fun newInstance(): RecipeListFragment {
            return RecipeListFragment()
        }
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(RecipeListModule(this)).inject(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recipe_list
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, recipeListViewModelFactory).get(RecipeListViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                onRecipesSuccess(it)
            }
        })
    }

    fun onRecipesSuccess(recipes: List<Recipe>) {
        Toast.makeText(context, "Recipes size : " + recipes.size, Toast.LENGTH_LONG).show()
    }

    fun onRecipesError(error: Throwable) {
        Toast.makeText(context, "Recipes error : " + error.message, Toast.LENGTH_LONG).show()
    }

}