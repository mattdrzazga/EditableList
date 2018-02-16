package com.example.com.rxroomdagger.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DatabaseModule {

    @Provides
    @Singleton
    static AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "note-db").build();
    }

    @Provides
    @Singleton
    static NoteDao provideNoteDao(AppDatabase db) {
        return db.noteDao();
    }
}
