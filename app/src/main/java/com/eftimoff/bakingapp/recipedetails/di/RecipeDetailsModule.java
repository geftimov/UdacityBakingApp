package com.eftimoff.bakingapp.recipedetails.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eftimoff.bakingapp.app.injection.qualifiers.ForApplication;
import com.eftimoff.bakingapp.app.injection.scopes.PerFragment;
import com.eftimoff.bakingapp.recipedetails.view.RecipeDetailsFragment;
import com.eftimoff.bakingapp.recipedetails.view.RecipeStepsAdapter;
import com.eftimoff.bakingapp.recipedetails.view.RecipeStepsAdapterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeDetailsModule {

    private RecipeDetailsFragment fragment;

    public RecipeDetailsModule(RecipeDetailsFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public RecipeStepsAdapter provideRecipeStepsAdapter(RecipeStepsAdapterImpl recipeStepsAdapter) {
        return recipeStepsAdapter;
    }

    @Provides
    @PerFragment
    public RecyclerView.LayoutManager provideLayoutManager(@ForApplication Context context) {
        return new LinearLayoutManager(context);
    }

}
