package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.com.rxroomdagger.db.RoomNote;
import com.example.com.rxroomdagger.ui.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MainViewModel extends ViewModel {
    private final NotesRepository repository;
    private final LiveData<List<Note>> notes;

    @Inject
    public MainViewModel(NotesRepository repository) {
        this.repository = repository;
        notes = Transformations.map(repository.getNotes(), input -> {
            List<Note> notes = new ArrayList<>(input.size());
            for (RoomNote note : input) {
                notes.add(NotesRepository.convert(note));
            }
            return notes;
        });
    }


    public LiveData<List<Note>> getNotes() {
        return notes;
    }
}
