package com.example.com.rxroomdagger.di;

import com.example.com.rxroomdagger.ui.notes.NotesDataSource;
import com.example.com.rxroomdagger.ui.notes.NotesRepository;

import dagger.Binds;
import dagger.Module;

@Module
public interface DataModule {
    @Binds
    NotesDataSource bindNotesDataSource(NotesRepository repository);
}
