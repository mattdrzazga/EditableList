package com.example.com.rxroomdagger.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.com.rxroomdagger.ui.notes.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public interface ViewModelModule {
    @Binds
    ViewModelProvider.Factory bindFactory(AppViewModelFactory factory);

    @Binds @IntoMap @ViewModelKey(MainViewModel.class) ViewModel bindMainViewModel(MainViewModel viewModel);
}
