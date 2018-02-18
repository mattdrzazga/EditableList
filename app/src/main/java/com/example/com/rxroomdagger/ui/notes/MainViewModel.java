package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.com.rxroomdagger.ui.notes.model.Note;

import java.util.List;

import javax.inject.Inject;


public class MainViewModel extends ViewModel {
    private final NotesRepository repository;
    private final LiveData<List<Note>> notes;

    @Inject
    public MainViewModel(NotesRepository repository) {
        this.repository = repository;
        notes = Transformations.map(repository.getNotes(), NotesRepository::convert);
    }


    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void newNote() {
        repository.addNote().subscribe();
    }

    public void deleteNote(@NonNull Note note) {
        repository.deleteNote(note).subscribe();
    }

    public void updateNote(@NonNull Note note, @NonNull String text) {
        if (text.isEmpty()) {
            deleteNote(note);
        } else {
            repository.updateNote(new Note(note.id, text)).subscribe();
        }
    }
}
