package com.eftimoff.bakingapp.recipedetails.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.view.BaseActivity;
import com.eftimoff.bakingapp.app.view.IntentExtras;

public class RecipeDetailsActivity extends BaseActivity {

    public static void start(Context context, Recipe recipe) {
        Intent starter = new Intent(context, RecipeDetailsActivity.class);
        starter.putExtra(IntentExtras.EXTRA_RECIPE, recipe);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, RecipeDetailsFragment.newInstance(getRecipe()))
                    .commit();
        }
    }

    private Recipe getRecipe() {
        return getIntent().getParcelableExtra(IntentExtras.EXTRA_RECIPE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recipe_details;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
