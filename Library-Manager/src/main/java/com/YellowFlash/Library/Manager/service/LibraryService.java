package com.YellowFlash.Library.Manager.service;

import com.YellowFlash.Library.Manager.Exceptions.*;
import com.YellowFlash.Library.Manager.model.Book;
import com.YellowFlash.Library.Manager.model.BorrowLog;
import com.YellowFlash.Library.Manager.model.Member;
import com.YellowFlash.Library.Manager.repository.BookRepository;
import com.YellowFlash.Library.Manager.repository.BorrowLogRepository;
import com.YellowFlash.Library.Manager.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final BorrowLogRepository borrowLogRepository;
    private final MemberRepository memberRepository;

    public List<BorrowLog> getBorrowHistory() {
        return borrowLogRepository.findAllByOrderByTimeStampDesc();
    }

    // Dodaje książkę, loguje info: "Dodano książkę: {title}"
    public Book addBook(String title, String author, String isbn) {
        Book book = Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .build();
        log.info("Book added {}", title);
        return bookRepository.save(book);

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().toList();
    }

    // Zwraca książki danego autora (case-insensitive)
    public List<Book> getByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author).stream().toList();
    }

    public Map<String, Long> authorStats() {
        return bookRepository.findAll().stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));
    }

    // Zwraca dostępne książki
    public List<Book> getAvailableBooks() {
        return bookRepository.findByIsAvailableTrue().stream().toList();
    }

    @Transactional
    public Book borrowBook(Long id, Long memberId) throws RuntimeException {
        Book bookToBorrow = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (!bookToBorrow.isAvailable()) throw new BookNotAvailableException(bookToBorrow.getTitle());
        bookToBorrow.setAvailable(false);
        Member memberToFind = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId));
        if (!memberToFind.isActive()) throw new MemberDeactivatedException(memberId);

        borrowLogRepository.save(BorrowLog.builder().member(memberToFind)
                .book(bookToBorrow)
                .action(BorrowLog.BorrowAction.Borrowed).build());
        log.info("Book {} has been borrowed by {}", bookToBorrow.getTitle(), memberToFind.getFullName());
        return bookToBorrow;
    }

    @Transactional
    public Book returnBook(Long id,Long memberId) {
        Book bookToBorrow = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (bookToBorrow.isAvailable()) throw new BookNotBorrowedException(bookToBorrow.getTitle());
        bookToBorrow.setAvailable(true);
        Member memberToFind = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(memberId));
        if (!memberToFind.isActive()) throw new MemberDeactivatedException(memberId);
        borrowLogRepository.save(BorrowLog.builder()
                .member(memberToFind)
                .book(bookToBorrow)
                .action(BorrowLog.BorrowAction.Returned).build());
        log.info("Book {} has been returned", bookToBorrow.getTitle());
        return bookToBorrow;
    }

    public void deleteBook(Long id) {
        Book bookToDelete = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (!bookToDelete.isAvailable())
            throw new BookNotAvailableException(bookToDelete.getTitle() + " can not be deleted");
        log.info("Book {} deleted ", bookToDelete.getTitle());
        bookRepository.delete(bookToDelete);

    }

    // Zwraca statystyki: ile książek ogółem i ile dostępnych
    public String getStats() {
        long totalBooks = bookRepository.findAll().size();
        long availableBooks = bookRepository.findByIsAvailableTrue().size();
        return "Total Books in the Library: " + totalBooks + "\n" + "Available Books: " + availableBooks;
    }

}
