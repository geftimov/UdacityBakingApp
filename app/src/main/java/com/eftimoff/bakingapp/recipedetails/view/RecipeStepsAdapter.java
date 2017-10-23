package com.eftimoff.bakingapp.recipedetails.view;

import android.support.v7.widget.RecyclerView;

import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.models.Step;

public abstract class RecipeStepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    interface Callback {

        void onStepClicked(Step step);

    }

    abstract void setRecipe(Recipe recipe);

    abstract void setCallback(Callback callback);

}
