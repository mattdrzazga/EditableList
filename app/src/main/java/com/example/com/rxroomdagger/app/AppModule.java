package com.example.com.rxroomdagger.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;

@Singleton
@Module
public interface AppModule {
    Context bindContext(App app);
}
