package com.example.com.rxroomdagger.ui.notes;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.com.rxroomdagger.R;
import com.example.com.rxroomdagger.ui.notes.model.Note;
import com.example.com.rxroomdagger.utils.ObjectUtils;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Binds;

import static com.example.com.rxroomdagger.utils.ObjectUtils.requireNonNull;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Note> notes = Collections.emptyList();
    private NotesCallback callback;

    public void setNotes(@NonNull List<Note> notes) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(this.notes, notes));
        this.notes = notes;
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = notes.get(position);
        holder.input.setText(note.note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setCallback(@NonNull NotesCallback callback) {
        this.callback = requireNonNull(callback);
    }

    public void removeAt(int position) {
        if (position >= 0 && position < notes.size()) {
            notes.remove(position);
            notifyItemRemoved(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.input) EditText input;
        @BindView(R.id.delete) ImageButton delete;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            delete.setOnClickListener(view -> {
                final int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    callback.onDelete(position, notes.get(position));
                }
            });
        }
    }

    static class DiffUtilCallback extends DiffUtil.Callback {
        private final List<Note> oldData;
        private final List<Note> newData;

        public DiffUtilCallback(List<Note> oldData, List<Note> newData) {
            this.oldData = requireNonNull(oldData);
            this.newData = requireNonNull(newData);
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).id == newData.get(newItemPosition).id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Note a = oldData.get(oldItemPosition);
            Note b = newData.get(newItemPosition);
            return a.id == b.id && a.note.equals(b.note);
        }
    }

    public interface NotesCallback {
        void onDelete(int position, @NonNull Note note);
    }
}
