package com.example.com.rxroomdagger.app;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class App extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
