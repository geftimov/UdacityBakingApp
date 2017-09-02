package com.eftimoff.bakingapp.recipedetails.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.replace
import com.eftimoff.bakingapp.app.models.Recipe
import com.eftimoff.bakingapp.app.view.BaseActivity
import com.eftimoff.bakingapp.app.view.EXTRA_RECIPE

class RecipeDetailsActivity : BaseActivity() {

    companion object {
        fun start(context: Context, recipe: Recipe) {
            val starter = Intent(context, RecipeDetailsActivity::class.java)
            starter.putExtra(EXTRA_RECIPE, recipe)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.replace(RecipeDetailsFragment.newInstance(getRecipe()))
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_recipe_list
    }

    private fun getRecipe(): Recipe {
        return intent.getParcelableExtra(EXTRA_RECIPE)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

}
