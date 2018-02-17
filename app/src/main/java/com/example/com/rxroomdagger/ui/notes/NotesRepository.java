package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.LiveData;

import com.example.com.rxroomdagger.db.NoteDao;
import com.example.com.rxroomdagger.db.RoomNote;
import com.example.com.rxroomdagger.ui.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotesRepository implements NotesDataSource {
    private final NoteDao dao;

    @Inject
    public NotesRepository(NoteDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<Note>> getNotesFlowable() {
        return dao.getAllFlowable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(roomNotes -> {
                    List<Note> notes = new ArrayList<>(roomNotes.size());
                    for (RoomNote note : roomNotes) {
                        notes.add(new Note(note.id, note.note));
                    }
                    return notes;
                });
    }

    @Override
    public LiveData<List<RoomNote>> getNotes() {
        return dao.getAll();
    }

    public Completable addNote() {
        final RoomNote newNote = new RoomNote();
        newNote.note = "";
        return Completable.fromCallable(() -> dao.insert(newNote)).subscribeOn(Schedulers.io());
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public Completable deleteNote(Note note) {
        return Completable.fromAction(() -> dao.delete(convert(note))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Note convert(RoomNote note) {
        return new Note(note.id, note.note);
    }

    public static RoomNote convert(Note note) {
        final RoomNote roomNote = new RoomNote();
        roomNote.id = note.id;
        roomNote.note = note.note;
        return roomNote;
    }
}
