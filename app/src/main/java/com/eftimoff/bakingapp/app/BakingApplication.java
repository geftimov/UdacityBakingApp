package com.eftimoff.bakingapp.app;

import android.app.Application;

import com.eftimoff.bakingapp.app.injection.AppComponent;
import com.eftimoff.bakingapp.app.injection.AppModule;
import com.eftimoff.bakingapp.app.injection.DaggerAppComponent;

public class BakingApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent component() {
        return component;
    }
}
