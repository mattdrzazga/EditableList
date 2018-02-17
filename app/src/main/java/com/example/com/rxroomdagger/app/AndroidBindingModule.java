package com.example.com.rxroomdagger.app;

import com.example.com.rxroomdagger.ui.notes.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface AndroidBindingModule {
    @ContributesAndroidInjector
    MainActivity mainActivity();
}
