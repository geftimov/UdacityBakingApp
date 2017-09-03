package com.eftimoff.bakingapp.recipestep.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.eftimoff.bakingapp.R
import com.eftimoff.bakingapp.app.extensions.replace
import com.eftimoff.bakingapp.app.models.Step
import com.eftimoff.bakingapp.app.view.BaseActivity
import com.eftimoff.bakingapp.app.view.EXTRA_STEP

class RecipeStepActivity : BaseActivity() {

    companion object {
        fun start(context: Context, step: Step) {
            val starter = Intent(context, RecipeStepActivity::class.java)
            starter.putExtra(EXTRA_STEP, step)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.replace(RecipeStepFragment.newInstance(getStep()))
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_recipe_step
    }

    private fun getStep(): Step {
        return intent.getParcelableExtra(EXTRA_STEP)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

}