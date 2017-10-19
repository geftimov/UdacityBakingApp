package com.eftimoff.bakingapp.recipedetails.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.replace
import com.eftimoff.bakingapp.app.extensions.toast
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.models.Step
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.app.view.EXTRA_RECIPE
import com.eftimoff.bakingapp.recipelist.di.RecipeDetailsModule
import com.eftimoff.bakingapp.recipestep.view.RecipeStepActivity
import com.eftimoff.bakingapp.recipestep.view.RecipeStepFragment
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.item_recipe_step.view.*
import javax.inject.Inject

class RecipeDetailsFragment : BaseFragment(), RecipeStepsAdapter.Callback {

    @Inject
    lateinit var recipeStepsAdapter: RecipeStepsAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager
    @Inject
    lateinit var recipeInfoWidgetManager: RecipeInfoWidgetManager


    private var isTablet: Boolean = false

    companion object {
        fun newInstance(recipe: Recipe): RecipeDetailsFragment {
            val recipeDetailsFragment = RecipeDetailsFragment()
            val bundle = Bundle()
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

        isTablet = resources.getBoolean(R.bool.isTablet)

        recipeStepsAdapter.setCallback(this)

        recipeSteps.adapter = recipeStepsAdapter
        recipeSteps.layoutManager = layoutManager

        recipeFab.setOnClickListener {
            recipeInfoWidgetManager.updateWidgetRecipe(getRecipe())
            toast(getString(R.string.widget_updated, getRecipe().name))
        }

        recipeStepsAdapter.setRecipe(getRecipe())

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getRecipe().name

        if (isTablet) recipeSteps.post { recipeSteps.findViewHolderForAdapterPosition(1).itemView.recipeStepContainer.performClick() }
    }

    private fun getRecipe(): Recipe {
        return arguments.getParcelable(EXTRA_RECIPE)
    }

    override fun onStepClicked(step: Step) {
        if (isTablet) {
            activity.supportFragmentManager.replace(R.id.fragment_inner_container, RecipeStepFragment.newInstance(step))
        } else {
            RecipeStepActivity.start(activity, step)
        }
    }

}