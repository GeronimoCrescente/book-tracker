package com.backend.booktrackerbackend.controllers;

import com.backend.booktrackerbackend.controllers.requests.CreateNoteRequest;
import com.backend.booktrackerbackend.controllers.requests.ModifyNoteRequest;
import com.backend.booktrackerbackend.controllers.responses.NoteResponse;
import com.backend.booktrackerbackend.models.Note;
import com.backend.booktrackerbackend.services.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteServices noteServices;

    @Autowired
    public NoteController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAll() {
        try {
            var notes = noteServices.findAll().stream()
                    .map(NoteResponse::from)
                    .toList();

            return notes.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(notes);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteResponse> getById(@PathVariable Integer noteId) {
        try {
            Optional<Note> note = noteServices.findById(noteId);

            return note.map(NoteResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<NoteResponse>> getByBookId(@PathVariable Integer bookId) {
        try {
            var notesByBook = noteServices.findByBookId(bookId).stream()
                    .map(NoteResponse::from)
                    .toList();

            return notesByBook.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(notesByBook);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Object> deleteNoteById(@PathVariable Integer noteId) {
        try {
            noteServices.deleteNoteById(noteId);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Note> createNewNote(@RequestBody CreateNoteRequest newNote) {
        try {
            Note created = noteServices.saveNote(newNote.toEntity());
            URI ubi = URI.create(String.format("/notes/%d", created.getId()));

            return ResponseEntity.created(ubi).body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Object> modifyNote(@PathVariable Integer noteId,
                                             @RequestBody ModifyNoteRequest modifiedNoteBody) {
        try {
            noteServices.updateNote(noteId, modifiedNoteBody);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
