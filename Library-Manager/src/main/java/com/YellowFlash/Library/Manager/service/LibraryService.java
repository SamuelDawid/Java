package com.YellowFlash.Library.Manager.service;

import com.YellowFlash.Library.Manager.model.Book;
import com.YellowFlash.Library.Manager.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final HashSet<Long> IdLog;

    // Dodaje książkę, loguje info: "Dodano książkę: {title}"
    public Book addBook(String title, String author, String isbn) {

        return new Book(
                generateId(),
                title,
                author,
                isbn,
                true,
                LocalDate.now()
        );

    }

    private Long generateId() {
        while (true) {
            var rnd = new Random().nextLong(0, 999999999);
            if (!IdLog.contains(rnd)) {
                IdLog.add(rnd);
                return rnd;
            }
        }
    }

    // Zwraca wszystkie książki
    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().toList();
    }

    // Zwraca książki danego autora (case-insensitive)
    public List<Book> getByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author).stream().toList();
    }

    // Zwraca dostępne książki
    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue().stream().toList();
    }

    // Wypożycza książkę — jeśli nie istnieje rzuć RuntimeException("Nie znaleziono: " +
// Jeśli niedostępna rzuć RuntimeException("Książka już wypożyczona")
// Ustaw available=false, zapisz, zaloguj
    @Transactional
    public Book borrowBook(Long id) throws RuntimeException {
        Book bookToBorrow = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!bookToBorrow.isAvailable()) throw new RuntimeException("Book is borrowed");
        bookToBorrow.setAvailable(false);
        return bookToBorrow;
    }

    // Zwraca książkę — jeśli available=true rzuć RuntimeException("Książka nie jest wyp
// Ustaw available=true, zapisz, zaloguj
    @Transactional
    public Book returnBook(Long id) {
        Book bookToBorrow = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        if (bookToBorrow.isAvailable()) throw new RuntimeException("Book is available");
        bookToBorrow.setAvailable(true);
        return bookToBorrow;
    }

    // Usuwa książkę — jeśli nie istnieje rzuć RuntimeException
    public void deleteBook(Long id) {
        Book bookToDelete = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(bookToDelete);

    }

    // Zwraca statystyki: ile książek ogółem i ile dostępnych
    public String getStats() {
        long totalBooks = bookRepository.findAll().size();
        long availableBooks = bookRepository.findByIsAvailableTrue().size();
        return "Total Books in the Library: " + totalBooks + "\n" + "Available Books: " + availableBooks;
    }

}
