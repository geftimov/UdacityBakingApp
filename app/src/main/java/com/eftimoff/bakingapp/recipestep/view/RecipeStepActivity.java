package com.eftimoff.bakingapp.recipestep.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.WindowManager;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Step;
import com.eftimoff.bakingapp.app.view.BaseActivity;
import com.eftimoff.bakingapp.app.view.IntentExtras;

public class RecipeStepActivity extends BaseActivity {

    public static void start(Context context, Step step) {
        Intent starter = new Intent(context, RecipeStepActivity.class);
        starter.putExtra(IntentExtras.EXTRA_STEP, step);
        context.startActivity(starter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().hide();
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().show();
                break;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, RecipeStepFragment.newInstance(getStep()))
                    .commit();
        }

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recipe_step;
    }

    private Step getStep() {
        return getIntent().getParcelableExtra(IntentExtras.EXTRA_STEP);
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
