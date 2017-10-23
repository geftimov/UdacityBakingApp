package com.eftimoff.bakingapp.recipelist.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.injection.AppComponent;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.view.BaseFragment;
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsActivity;
import com.eftimoff.bakingapp.recipelist.di.RecipeListModule;
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModel;
import com.eftimoff.bakingapp.recipelist.viewmodels.RecipeListViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class RecipeListFragment extends BaseFragment implements RecipeAdapter.Callback {

    @BindView(R.id.recipeList)
    RecyclerView recipeList;

    @Inject
    RecipeListViewModelFactory recipeListViewModelFactory;
    @Inject
    RecipeAdapter recipeAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;

    private RecipeListViewModel viewModel;

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

        viewModel = ViewModelProviders.of(this, recipeListViewModelFactory).get(RecipeListViewModel.class);
        viewModel.getData().observe(this, recipes -> recipeAdapter.setRecipes(recipes));
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        RecipeDetailsActivity.start(getContext(), recipe);
    }

}
