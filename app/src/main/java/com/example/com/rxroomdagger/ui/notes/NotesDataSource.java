package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.LiveData;

import com.example.com.rxroomdagger.db.RoomNote;
import com.example.com.rxroomdagger.ui.notes.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface NotesDataSource {
    LiveData<List<RoomNote>> getNotes();
    Completable updateNote(Note note);
    Completable deleteNote(Note note);
}
