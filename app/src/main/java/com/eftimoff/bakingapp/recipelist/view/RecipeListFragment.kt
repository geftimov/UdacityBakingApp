package com.eftimoff.bakingapp.recipelist.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.eftimoff.bakingapp.BakingApplication
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.view.BaseFragment
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule
import javax.inject.Inject

class RecipeListFragment : BaseFragment() {

    @Inject
    lateinit var shared: SharedPreferences

    companion object {
        fun newInstance(): RecipeListFragment {
            return RecipeListFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BakingApplication.component.plus(RecipeListModule()).inject(this)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_recipe_list
    }

}