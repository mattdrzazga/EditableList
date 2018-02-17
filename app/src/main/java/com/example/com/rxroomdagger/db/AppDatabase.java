package com.example.com.rxroomdagger.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {
        RoomNote.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
