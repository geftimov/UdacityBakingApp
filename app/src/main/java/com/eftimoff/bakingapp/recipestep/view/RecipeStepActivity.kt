package com.eftimoff.bakingapp.recipestep.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
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
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                actionBar?.hide()
                supportActionBar?.hide()
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                actionBar?.show()
                supportActionBar?.show()
            }
        }

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