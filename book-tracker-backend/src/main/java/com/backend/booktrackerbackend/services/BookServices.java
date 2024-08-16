package com.backend.booktrackerbackend.services;

import com.backend.booktrackerbackend.controllers.requests.ModifyBookRequest;
import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.Category;
import com.backend.booktrackerbackend.models.User;
import com.backend.booktrackerbackend.repositories.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServices {

    private final BookRepository bookRepository;

    @Autowired
    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> findByUser(User user) {
        return bookRepository.findByUser(user);
    }

    public List<Book> findByState(Integer user, Boolean readState) {
        return bookRepository.findByStateAndUser(user, readState);
    }

    public List<Book> findByCategory(Integer user, Integer category) {
        return bookRepository.findByCategoryAndUser(user, category);
    }

    public List<Book> findByName(Integer user, String name) {
        return bookRepository.findByNameAndUser(user, name);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    public void updateBook(Integer bookId, ModifyBookRequest modifyBookRequest) {
        Optional<Book> optionalBook = findById(bookId);
        if (optionalBook.isPresent()) {
            Book updateEntity = optionalBook.get();

            updateEntity.setAuthor(modifyBookRequest.getAuthor() != null ?
                    modifyBookRequest.getAuthor() : updateEntity.getAuthor());

            updateEntity.setDescription(modifyBookRequest.getDescription() != null ?
                    modifyBookRequest.getDescription() : updateEntity.getDescription());

            updateEntity.setReadState(modifyBookRequest.getReadState() != null ?
                    modifyBookRequest.getReadState() : updateEntity.getReadState());

            saveBook(updateEntity);
        }
    }

}
