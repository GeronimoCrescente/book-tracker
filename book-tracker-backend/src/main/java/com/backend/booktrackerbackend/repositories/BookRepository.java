package com.backend.booktrackerbackend.repositories;

import com.backend.booktrackerbackend.models.Book;
import com.backend.booktrackerbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByUser(User user);

    @Query("SELECT b FROM Book b WHERE b.user.id = :userId AND b.readState = :readState")
    List<Book> findByStateAndUser(@Param("userId") Integer userId, @Param("readState") Boolean readState);


    @Query("SELECT b FROM Book b WHERE b.user.id = :userId AND b.category.id = :categoryId")
    List<Book> findByCategoryAndUser(@Param("userId") Integer userId,@Param("categoryId") Integer categoryId);

    @Query("SELECT b FROM Book b WHERE b.user.id = :userId AND b.name LIKE %:name%")
    List<Book> findByNameAndUser(@Param("userId") Integer userId,@Param("name") String name);
}
