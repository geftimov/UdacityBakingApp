package com.eftimoff.bakingapp.recipelist.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.images.VideoThumbnailUrl;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.models.Step;
import com.eftimoff.bakingapp.app.view.BaseViewHolder;

import butterknife.BindView;

public class RecipeViewHolder extends BaseViewHolder<Recipe> {

    @BindView(R.id.recipeContainer)
    ViewGroup recipeContainer;
    @BindView(R.id.recipeName)
    TextView recipeName;
    @BindView(R.id.recipeServings)
    TextView recipeServings;
    @BindView(R.id.recipeImage)
    ImageView recipeImage;

    private final RecipeAdapter.Callback callback;

    public RecipeViewHolder(View itemView, RecipeAdapter.Callback callback) {
        super(itemView);
        this.callback = callback;
    }

    @Override
    protected void bind(Recipe recipe) {
        recipeContainer.setOnClickListener(view -> {
            if (callback != null) {
                callback.onRecipeClicked(recipe);
            }
        });

        recipeName.setText(recipe.getName());
        recipeServings.setText(String.valueOf(recipe.getServings()));

        if (recipe.getImage() != null && !recipe.getImage().isEmpty()) {
            Glide.with(itemView).load(recipe.getImage()).into(recipeImage);
        } else {
            Step step = recipe.getSteps().get(recipe.getSteps().size() - 1);
            String videoUrl = step.getVideoURL();

            if (videoUrl != null) {
                Glide.with(itemView).load(new VideoThumbnailUrl(videoUrl)).into(recipeImage);
            }
        }
    }

}
