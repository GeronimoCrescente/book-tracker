package com.backend.booktrackerbackend.controllers;

import com.backend.booktrackerbackend.controllers.requests.CreateBookRequest;
import com.backend.booktrackerbackend.controllers.requests.ModifyBookRequest;
import com.backend.booktrackerbackend.controllers.responses.BookResponse;
import com.backend.booktrackerbackend.controllers.responses.BookResponseSimplified;
import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.User;
import com.backend.booktrackerbackend.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServices bookServices;

    @Autowired
    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAll() {
        try {
            var books = bookServices.findAll().stream()
                    .map(BookResponse::from)
                    .toList();

            return books.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(books);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace(); // what exception is caught
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getById(@PathVariable Integer bookId) {
        try {
            Optional<Book> book = bookServices.findById(bookId);

            return book.map(BookResponse::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.noContent().build());

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<BookResponseSimplified>> getBooksByUser(@RequestBody User user) {
        try {
            var userBooks = bookServices.findByUser(user).stream()
                    .map(BookResponseSimplified::from)
                    .toList();

            return userBooks.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(userBooks);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/state/{userStateId}")
    public ResponseEntity<List<BookResponseSimplified>> getBooksByState(@PathVariable Integer userStateId,
                                                                        @RequestParam("state") Boolean readState) {
        try {
            var stateBooks = bookServices.findByState(userStateId, readState).stream()
                    .map(BookResponseSimplified::from)
                    .toList();

            return stateBooks.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(stateBooks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/category/{userCategoryId}")
    public ResponseEntity<List<BookResponseSimplified>> getBooksByCategory(@PathVariable Integer userCategoryId,
                                                                        @RequestParam("category") Integer categoryId) {
        try {
            var stateBooks = bookServices.findByCategory(userCategoryId, categoryId).stream()
                    .map(BookResponseSimplified::from)
                    .toList();

            return stateBooks.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(stateBooks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/name/{userNameId}")
    public ResponseEntity<List<BookResponseSimplified>> getBooksByCategory(@PathVariable Integer userNameId,
                                                                           @RequestParam("name") String name) {
        try {
            var stateBooks = bookServices.findByName(userNameId, name).stream()
                    .map(BookResponseSimplified::from)
                    .toList();

            return stateBooks.isEmpty() ?
                    ResponseEntity.noContent().build() :
                    ResponseEntity.ok(stateBooks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Book> createNewBook(@RequestBody CreateBookRequest newBook) {
        try {
            Book created = bookServices.saveBook(newBook.toEntity());
            URI ubi = URI.create(String.format("/books/%d", created.getId()));

            return ResponseEntity.created(ubi).body(created);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Object> deleteBook(@PathVariable Integer bookId) {
        try {
            bookServices.deleteBookById(bookId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Object> modifyBook(@PathVariable Integer bookId,
                                                        @RequestBody ModifyBookRequest modifiedBookBody) {
        try {
            bookServices.updateBook(bookId, modifiedBookBody);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
