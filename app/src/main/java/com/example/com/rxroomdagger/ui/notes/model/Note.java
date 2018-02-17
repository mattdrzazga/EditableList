package com.example.com.rxroomdagger.ui.notes.model;

import android.support.annotation.NonNull;

public class Note {
    public final long id;
    @NonNull public final String note;

    public Note(long id, @NonNull String note) {
        this.id = id;
        this.note = note;
    }
}
