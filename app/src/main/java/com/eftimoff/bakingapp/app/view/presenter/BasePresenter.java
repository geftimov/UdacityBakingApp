package com.eftimoff.bakingapp.app.view.presenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements Presenter {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void clear() {
        compositeDisposable.clear();
    }
}
