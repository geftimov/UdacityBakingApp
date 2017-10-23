package com.eftimoff.bakingapp.recipedetails.view;

import android.view.View;
import android.widget.TextView;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Ingredient;
import com.eftimoff.bakingapp.app.view.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

public class RecipeIngredientsViewHolder extends BaseViewHolder<List<Ingredient>> {

    @BindView(R.id.recipeStepIngredientsList)
    TextView recipeStepIngredientsList;

    public RecipeIngredientsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bind(List<Ingredient> ingredients) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            stringBuilder.append(ingredient.toString());
            if (i != ingredients.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        recipeStepIngredientsList.setText(stringBuilder.toString());
    }

}
