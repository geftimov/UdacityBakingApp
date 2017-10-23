package com.eftimoff.bakingapp.recipelist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eftimoff.bakingapp.R;
import com.eftimoff.bakingapp.app.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeAdapterImpl extends RecipeAdapter {

    private List<Recipe> recipes = new ArrayList<>();
    private Callback callback;

    @Inject
    public RecipeAdapterImpl() {

    }

    @Override
    void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, callback);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}
