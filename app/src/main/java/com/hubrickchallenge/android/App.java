package com.hubrickchallenge.android;

import android.app.Application;

import com.hubrickchallenge.android.dagger.AppComponent;
import com.hubrickchallenge.android.dagger.AppModule;

import com.hubrickchallenge.android.dagger.DaggerAppComponent;
import com.hubrickchallenge.android.managers.FeedConsumer;

import javax.inject.Inject;

/**
 * Created by joaocruz04 on 11/07/2017.
 */

public class App extends Application {

    private AppComponent appComponent;
    private static App instance;

    @Inject
    FeedConsumer feedConsumer;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
        appComponent.inject(this);

    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
