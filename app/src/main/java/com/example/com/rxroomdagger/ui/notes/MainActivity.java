package com.example.com.rxroomdagger.ui.notes;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.com.rxroomdagger.R;
import com.example.com.rxroomdagger.ui.notes.model.Note;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements NotesAdapter.NotesCallback {
    private static final String TAG = "TQAG";
    @BindView(R.id.questions)
    RecyclerView questions;

    final NotesAdapter notesAdapter = new NotesAdapter();

    @Inject
    ViewModelProvider.Factory factory;

    MainViewModel viewModel;

    @Inject
    NotesRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        notesAdapter.setCallback(this);
        questions.setAdapter(notesAdapter);

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        initVMObservers();
//        initReactiveObservers();
    }

    private void initReactiveObservers() {
        repository.getNotesFlowable().subscribe(notes -> {
            Log.d(TAG, "onCreate: " + notes.size());
            notesAdapter.setNotes(notes);
        });
    }

    private void initVMObservers() {
        viewModel.getNotes().observe(this, notes -> {
            Log.d(TAG, "initVMObservers: " + notes.size());
            notesAdapter.setNotes(notes);
        });
    }


    @OnClick(R.id.floatingActionButton)
    public void addNote() {
        repository.addNote().subscribe();
    }

    @Override
    public void onDelete(int position, @NonNull Note note) {
        repository.deleteNote(note)
                .subscribe(() -> notesAdapter.removeAt(position), throwable -> {});
    }
}
