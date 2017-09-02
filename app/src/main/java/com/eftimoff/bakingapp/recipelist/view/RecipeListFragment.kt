package com.eftimoff.bakingapp.recipelist.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsActivity
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModel
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModelFactory
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import javax.inject.Inject


class RecipeListFragment : BaseFragment(), RecipeAdapter.Callback {

    @Inject
    lateinit var recipeListViewModelFactory: RecipeListViewModelFactory
    @Inject
    lateinit var recipeAdapter: RecipeAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

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

        recipeAdapter.setCallback(this)
        recipeList.adapter = recipeAdapter
        recipeList.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this, recipeListViewModelFactory).get(RecipeListViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                onRecipesSuccess(it)
            }
        })
    }

    fun onRecipesSuccess(recipes: List<Recipe>) {
        recipeAdapter.setRecipes(recipes)
    }

    override fun onRecipeClicked(recipe: Recipe) {
        RecipeDetailsActivity.start(context, recipe)
    }

}