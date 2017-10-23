package com.eftimoff.bakingapp.recipelist.view;

import android.support.v7.widget.RecyclerView;

import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.List;

public abstract class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    interface Callback {
        void onRecipeClicked(Recipe recipe);
    }

    abstract void setRecipes(List<Recipe> recipes);

    abstract void setCallback(Callback callback);
    
}
