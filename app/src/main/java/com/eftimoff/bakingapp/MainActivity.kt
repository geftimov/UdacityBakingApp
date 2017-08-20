package com.eftimoff.bakingapp

import android.os.Bundle
import com.eftimoff.bakingapp.app.extensions.replace
import com.eftimoff.bakingapp.app.view.BaseActivity
import com.eftimoff.bakingapp.recipelist.view.RecipeListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.replace(RecipeListFragment.newInstance())
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}
