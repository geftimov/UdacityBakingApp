package com.eftimoff.bakingapp.recipelist.view

import android.os.Bundle
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.replace
import com.eftimoff.bakingapp.app.view.BaseActivity

class RecipeListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.replace(RecipeListFragment.newInstance())
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_recipe_list
    }
}
