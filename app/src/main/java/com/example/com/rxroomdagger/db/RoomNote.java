package com.example.com.rxroomdagger.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class RoomNote {
    @PrimaryKey(autoGenerate = true) public long id;
    @NonNull public String note;
}
