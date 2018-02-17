package com.example.com.rxroomdagger.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Allows injecting arguments into constructor.
 */
@Singleton
public class AppViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> providers;

    @Inject
    public AppViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providers) {
        this.providers = providers;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> provider = providers.get(modelClass);

        if (provider == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : providers.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    provider = entry.getValue();
                    break;
                }
            }
        }

        if (provider == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }

        try {
            //noinspection unchecked
            return (T) provider.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}