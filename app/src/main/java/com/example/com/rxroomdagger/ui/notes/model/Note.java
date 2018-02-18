package com.example.com.rxroomdagger.ui.notes.model;

import android.support.annotation.NonNull;

import static com.example.com.rxroomdagger.utils.ObjectUtils.requireNonNull;

public class Note {
    public final long id;
    @NonNull
    private String note;

    public Note(long id, @NonNull String note) {
        this.id = id;
        this.note = requireNonNull(note);
    }

    public Note(long id) {
        this.id = id;
        note = "";
    }

    /**
     * This method assigns note to its backing field.
     * It is used to prohibit null string assignment.
     * @param note Must not be null
     */
    public void setNote(@NonNull String note) {
        this.note = requireNonNull(note);
    }

    @NonNull
    public String getNote() {
        return note;
    }
}
