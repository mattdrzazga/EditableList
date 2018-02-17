package com.example.com.rxroomdagger.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Singleton
@Module
public interface AppModule {
    @Binds Context bindContext(App app);
}
