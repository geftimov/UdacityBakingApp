package com.eftimoff.bakingapp.recipelist.di;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.injection.qualifiers.ForApplication;
import com.eftimoff.bakingapp.app.injection.scopes.PerFragment;
import com.eftimoff.bakingapp.recipelist.presenter.RecipeListPresenter;
import com.eftimoff.bakingapp.recipelist.presenter.RecipeListPresenterImpl;
import com.eftimoff.bakingapp.recipelist.view.RecipeAdapter;
import com.eftimoff.bakingapp.recipelist.view.RecipeAdapterImpl;
import com.eftimoff.bakingapp.recipelist.view.RecipeListFragment;
import com.eftimoff.bakingapp.recipelist.view.RecipeListView;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeListModule {

    private RecipeListFragment fragment;

    public RecipeListModule(RecipeListFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public RecipeListView provideRecipeListView() {
        return fragment;
    }

    @Provides
    @PerFragment
    public RecipeListPresenter provideRecipeListPresenter(RecipeListPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    public RecipeAdapter provideRecipeAdapter(RecipeAdapterImpl recipeAdapter) {
        return recipeAdapter;
    }

    @Provides
    @PerFragment
    public RecyclerView.LayoutManager provideLayoutManager(@ForApplication Context context) {
        return new GridLayoutManager(context, context.getResources().getInteger(R.integer.recipe_list_column_size));
    }

}
