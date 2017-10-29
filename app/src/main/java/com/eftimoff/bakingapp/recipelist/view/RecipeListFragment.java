package com.eftimoff.bakingapp.recipelist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.injection.AppComponent;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.view.BaseFragment;
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsActivity;
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule;
import com.eftimoff.bakingapp.recipelist.presenter.RecipeListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class RecipeListFragment extends BaseFragment implements RecipeListView, RecipeAdapter.Callback {

    @BindView(R.id.recipeList)
    RecyclerView recipeList;

    @Inject
    RecipeListPresenter presenter;
    @Inject
    RecipeAdapter recipeAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;

    public static RecipeListFragment newInstance() {
        return new RecipeListFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recipe_list;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.plus(new RecipeListModule(this)).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeAdapter.setCallback(this);
        recipeList.setAdapter(recipeAdapter);
        recipeList.setLayoutManager(layoutManager);

        presenter.loadRecipes();
    }

    @Override
    public void showRecipeList(List<Recipe> recipes) {
        recipeAdapter.setRecipes(recipes);
    }

    @Override
    public void showRecipeError() {
        Toast.makeText(getContext(), "Recipe error while fetching from backend.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        RecipeDetailsActivity.start(getContext(), recipe);
    }
}
