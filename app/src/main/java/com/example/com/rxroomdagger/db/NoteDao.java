package com.example.com.rxroomdagger.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

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
    Flowable<List<RoomNote>> getAllFlowable();

    @Query("SELECT * FROM notes")
    LiveData<List<RoomNote>> getAll();
}
