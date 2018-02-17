package com.example.com.rxroomdagger.app;

import com.example.com.rxroomdagger.db.DatabaseModule;
import com.example.com.rxroomdagger.di.DataModule;
import com.example.com.rxroomdagger.di.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidBindingModule.class,
        AppModule.class,
        DatabaseModule.class,
        ViewModelModule.class,
        DataModule.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}
