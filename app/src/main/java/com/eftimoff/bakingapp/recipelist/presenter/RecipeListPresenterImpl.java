package com.eftimoff.bakingapp.recipelist.presenter;

import com.eftimoff.bakingapp.app.models.Recipe;
import com.eftimoff.bakingapp.app.repositories.RecipeRepository;
import com.eftimoff.bakingapp.app.view.presenter.BasePresenter;
import com.eftimoff.bakingapp.recipelist.view.RecipeListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecipeListPresenterImpl extends BasePresenter implements RecipeListPresenter {

    private final RecipeListView view;
    private final RecipeRepository recipeRepository;

    @Inject
    public RecipeListPresenterImpl(RecipeListView view,
                                   RecipeRepository recipeRepository) {
        this.view = view;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void loadRecipes() {
        compositeDisposable.add(recipeRepository.loadRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError));
    }

    private void onSuccess(List<Recipe> recipes) {
        view.showRecipeList(recipes);
    }

    private void onError(Throwable throwable) {
        view.showRecipeError();
    }
}
