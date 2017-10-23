package com.eftimoff.bakingapp.recipelist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.view.BaseActivity;

public class RecipeListActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, RecipeListFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recipe_list;
    }

}
