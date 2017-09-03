package com.eftimoff.bakingapp.recipestep.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.injection.AppComponent
import com.eftimoff.bakingapp.app.models.Step
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.app.view.EXTRA_STEP

class RecipeStepFragment : BaseFragment() {

//    @Inject
//    lateinit var recipeStepsAdapter: RecipeStepsAdapter
//    @Inject
//    lateinit var layoutManager: RecyclerView.LayoutManager

    companion object {
        fun newInstance(step: Step): RecipeStepFragment {
            val recipeStepFragment = RecipeStepFragment()
            val bundle: Bundle = Bundle()
            bundle.putParcelable(EXTRA_STEP, step)
            recipeStepFragment.arguments = bundle
            return recipeStepFragment
        }
    }

    override fun inject(appComponent: AppComponent) {
//        appComponent.plus(RecipeDetailsModule(this)).inject(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recipe_details
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = getStep().shortDescription
    }

    private fun getStep(): Step {
        return arguments.getParcelable(EXTRA_STEP)
    }

}