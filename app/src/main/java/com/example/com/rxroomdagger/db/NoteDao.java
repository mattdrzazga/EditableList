package com.example.com.rxroomdagger.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {
    @Insert(onConflict = REPLACE)
    long insert(RoomNote note);

    @Delete
    void delete(RoomNote note);

    @Update
    void update(RoomNote note);

    @Query("SELECT * FROM notes")
    List<RoomNote> getAll();
}
