package com.eftimoff.bakingapp.recipedetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.injection.AppComponent;
import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.models.Step;
import com.eftimoff.bakingapp.app.view.BaseFragment;
import com.eftimoff.bakingapp.app.view.IntentExtras;
import com.eftimoff.bakingapp.recipedetails.di.RecipeDetailsModule;
import com.eftimoff.bakingapp.recipestep.view.RecipeStepActivity;
import com.eftimoff.bakingapp.recipestep.view.RecipeStepFragment;
import com.eftimoff.bakingapp.widgets.RecipeInfoWidgetManager;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.OnClick;

public class RecipeDetailsFragment extends BaseFragment implements RecipeStepsAdapter.Callback {

    @BindBool(R.bool.isTablet)
    boolean isTablet;

    @BindView(R.id.recipeSteps)
    RecyclerView recipeSteps;
    @BindView(R.id.recipeFab)
    FloatingActionButton recipeFab;

    @Inject
    RecipeStepsAdapter recipeStepsAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    RecipeInfoWidgetManager recipeInfoWidgetManager;

    public static RecipeDetailsFragment newInstance(Recipe recipe) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(IntentExtras.EXTRA_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recipe_details;
    }

    @Override
    protected void inject(AppComponent appComponent) {
        appComponent.plus(new RecipeDetailsModule(this)).inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recipeSteps.setAdapter(recipeStepsAdapter);
        recipeSteps.setLayoutManager(layoutManager);

        recipeStepsAdapter.setCallback(this);
        recipeStepsAdapter.setRecipe(getRecipe());

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getRecipe().getName());
        }

        if (isTablet) {
            recipeSteps.post(() -> recipeSteps.findViewHolderForAdapterPosition(1).itemView.findViewById(R.id.recipeStepContainer).performClick());
        }
    }

    private Recipe getRecipe() {
        return getArguments().getParcelable(IntentExtras.EXTRA_RECIPE);
    }

    @OnClick(R.id.recipeFab)
    public void onRecipeFabClick() {
        recipeInfoWidgetManager.updateWidgetRecipe(getRecipe());
        Toast.makeText(getContext(), getString(R.string.widget_updated, getRecipe().getName()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepClicked(Step step) {
        if (isTablet) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_inner_container, RecipeStepFragment.newInstance(step))
                    .commit();
        } else {
            RecipeStepActivity.start(getContext(), step);
        }
    }

}
