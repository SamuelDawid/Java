package com.YellowFlash.Library.Manager.repository;

import com.YellowFlash.Library.Manager.model.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<@NonNull Book,@NonNull Long> {
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByIsAvailableTrue();
    List<Book> findByTitleContainingIgnoreCase(String phrase);
    Optional<Book> findByIsbn(String isbn);
    long countByIsAvailableTrue();
    //@Query("SELECT b FROM b WHERE YEAR(b.addedAt) = :year")
    //List<Book> findAddedInYear(@Param("year") int years);



}
