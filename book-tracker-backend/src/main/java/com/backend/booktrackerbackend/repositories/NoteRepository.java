package com.backend.booktrackerbackend.repositories;

import com.backend.booktrackerbackend.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByBookId(Integer bookId);
}
