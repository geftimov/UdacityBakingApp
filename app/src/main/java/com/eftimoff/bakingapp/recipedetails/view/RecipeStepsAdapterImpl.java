package com.eftimoff.bakingapp.recipedetails.view;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.models.Step;

import javax.inject.Inject;

public class RecipeStepsAdapterImpl extends RecipeStepsAdapter {

    private Recipe recipe;
    private Callback callback;

    private int selectedPosition = -1;

    @Inject
    public RecipeStepsAdapterImpl() {

    }

    @Override
    void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.item_recipe_step_ingredients:
                return new RecipeIngredientsViewHolder(view);
            case R.layout.item_recipe_step:
                return new RecipeStepsViewHolder(view);
            default:
                return new RecipeStepsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecipeIngredientsViewHolder) {
            ((RecipeIngredientsViewHolder) holder).bind(recipe.getIngredients());
        } else if (holder instanceof RecipeStepsViewHolder) {
            RecipeStepsViewHolder viewHolder = (RecipeStepsViewHolder) holder;
            Step step = recipe.getSteps().get(position - 1);
            viewHolder.bind(step);

            if (viewHolder.itemView.getContext().getResources().getBoolean(R.bool.isTablet)) {
                if (selectedPosition == position) {
                    holder.itemView.setBackgroundColor(Color.LTGRAY);
                } else {
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            viewHolder.itemView.findViewById(R.id.recipeStepContainer).setOnClickListener(view -> {
                if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = viewHolder.getAdapterPosition();
                    notifyItemChanged(selectedPosition);
                    callback.onStepClicked(step);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_recipe_step_ingredients;
        }
        return R.layout.item_recipe_step;
    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size() + 1;
    }

}
