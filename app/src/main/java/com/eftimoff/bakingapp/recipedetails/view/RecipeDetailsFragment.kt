package com.eftimoff.bakingapp.recipedetails.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.app.view.EXTRA_RECIPE
import com.eftimoff.bakingapp.recipelist.di.RecipeDetailsModule
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import javax.inject.Inject

class RecipeDetailsFragment : BaseFragment() {

    @Inject
    lateinit var recipeStepsAdapter: RecipeStepsAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance(recipe: Recipe): RecipeDetailsFragment {
            val recipeDetailsFragment = RecipeDetailsFragment()
            val bundle: Bundle = Bundle()
            bundle.putParcelable(EXTRA_RECIPE, recipe)
            recipeDetailsFragment.arguments = bundle
            return recipeDetailsFragment
        }
    }

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(RecipeDetailsModule(this)).inject(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recipe_details
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeSteps.adapter = recipeStepsAdapter
        recipeSteps.layoutManager = layoutManager

        recipeStepsAdapter.setRecipe(getRecipe())

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getRecipe().name
    }

    private fun getRecipe(): Recipe {
        return arguments.getParcelable(EXTRA_RECIPE)
    }

}