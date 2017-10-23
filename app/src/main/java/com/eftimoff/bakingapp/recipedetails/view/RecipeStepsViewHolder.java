package com.eftimoff.bakingapp.recipedetails.view;

import android.view.View;
import android.widget.TextView;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Step;
import com.eftimoff.bakingapp.app.view.BaseViewHolder;

import butterknife.BindView;

public class RecipeStepsViewHolder extends BaseViewHolder<Step> {

    @BindView(R.id.recipeStepTitle)
    TextView recipeStepTitle;
    @BindView(R.id.recipeStepNumber)
    TextView recipeStepNumber;

    public RecipeStepsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void bind(Step step) {
        recipeStepTitle.setText(step.getShortDescription());
        recipeStepNumber.setText(String.valueOf(step.getId() + 1));
    }
}
