package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.com.rxroomdagger.db.NoteDao;
import com.example.com.rxroomdagger.db.RoomNote;
import com.example.com.rxroomdagger.ui.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.com.rxroomdagger.utils.ObjectUtils.requireNonNull;

public class NotesRepository implements NotesDataSource {
    private final NoteDao dao;

    @Inject
    public NotesRepository(NoteDao dao) {
        this.dao = dao;
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
    public Completable updateNote(Note note) {
        return Completable.fromAction(() -> dao.update(convert(note)))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable deleteNote(Note note) {
        return Completable.fromAction(() -> dao.delete(convert(note))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Note convert(RoomNote note) {
        return new Note(note.id, note.note);
    }

    public static List<Note> convert(@NonNull List<RoomNote> notes) {
        List<Note> converted = new ArrayList<>(requireNonNull(notes).size());
        for (RoomNote note : notes) {
            converted.add(convert(note));
        }
        return converted;
    }

    public static RoomNote convert(Note note) {
        final RoomNote roomNote = new RoomNote();
        roomNote.id = note.id;
        roomNote.note = note.getNote();
        return roomNote;
    }
}
