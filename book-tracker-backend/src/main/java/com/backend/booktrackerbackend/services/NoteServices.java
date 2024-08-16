package com.backend.booktrackerbackend.services;

import com.backend.booktrackerbackend.controllers.requests.ModifyNoteRequest;
import com.backend.booktrackerbackend.models.Note;
import com.backend.booktrackerbackend.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServices {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServices(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Integer noteId) {
        return noteRepository.findById(noteId);
    }

    public List<Note> findByBookId(Integer bookId) {
        return noteRepository.findByBookId(bookId);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNoteById(Integer noteId) {
        noteRepository.deleteById(noteId);
    }

    public void updateNote(Integer noteId, ModifyNoteRequest modifyNoteRequest) {
        Optional<Note> optionalNote = findById(noteId);
        if (optionalNote.isPresent()) {
            Note updateEntity = optionalNote.get();

            updateEntity.setTittle(modifyNoteRequest.getTittle() != null ?
                    modifyNoteRequest.getTittle() : updateEntity.getTittle());

            updateEntity.setBody(modifyNoteRequest.getBody() != null ?
                    modifyNoteRequest.getBody() : updateEntity.getBody());

            saveNote(updateEntity);
        }
    }
}
