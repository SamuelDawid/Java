package com.YellowFlash.Library.Manager.service;

import com.YellowFlash.Library.Manager.model.Book;
import com.YellowFlash.Library.Manager.model.BorrowLog;
import com.YellowFlash.Library.Manager.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Component
public class MenuHandler {
    private final LibraryService libraryService;
    private final MemberService memberService;
    private final Scanner scanner;

    public Member showLoginMenu() {
        while (true){
            printLoginMenu();
            System.out.print("Wybierz: ");
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> {
                    Member m = handleLogin();
                    if(m != null) return m;
                }
                case "2" -> {
                    Member m = handleRegister();
                    if(m != null) return m;
                }
                case "3" ->{
                    System.out.println("Do widzenia");
                    throw new RuntimeException("Exiting");
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }

    private Member handleRegister() {
        System.out.println("First Name: ");
        String firstNameRegister = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastNameRegister = scanner.nextLine();
        System.out.println("Email: ");
        String emailRegister = scanner.nextLine();
        System.out.println("Phone Number: ");
        String phoneNumberRegister = scanner.nextLine();

        return memberService.registerMember(firstNameRegister,lastNameRegister,emailRegister,phoneNumberRegister);
    }


    private Member handleLogin() {
        System.out.println("email: ");
        String emailLogin = scanner.nextLine();
        return memberService.login(emailLogin);
    }

    public void showMenu(Member currentMember) {
        while (true) {
            printMenu();
            System.out.print("Wybierz: ");
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> handleAddBook(scanner);
                case "2" -> handleListAll();
                case "3" -> handleSearchByAuthor(scanner);
                case "4" -> handleBorrow(scanner, currentMember);
                case "5" -> handleReturn(scanner,currentMember);
                case "6" -> handleAvailable();
                case "7" -> handleDelete();
                case "8" -> handleSearchEverywhere();
                case "9" -> handleShowBooksHistory();
                case "0" -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }

    private void handleShowBooksHistory() {
        List<BorrowLog> logs = libraryService.getBorrowHistory();
        if (logs.isEmpty()) {
            System.out.println("No history yet");
            return;
        }
        System.out.println("History");
        for (BorrowLog log : logs) {
            System.out.printf("[%s] %s — %s%n",
                    log.getTimeStamp(),
                    log.getAction(),
                    log.getBook().getTitle());
        }
    }

    private void handleSearchEverywhere() {
        libraryService.authorStats();

    }

    private void handleDelete() {
        System.out.println("Book id: ");
        String idInput = scanner.nextLine();
        try {
            libraryService.deleteBook(Long.parseLong(idInput));

        } catch (RuntimeException e) {
            throw new RuntimeException("Wrong Id");
        }
    }

    private void handleAvailable() {
        if (libraryService.getAvailableBooks().isEmpty())
            System.out.println(("No books avaliable"));

        for (Book b : libraryService.getAvailableBooks())
            printBook(b);

    }

    private void handleReturn(Scanner scanner,Member currentMember) {
        System.out.println("Provide book id: ");
        try {
            Long idInput = Long.parseLong(scanner.nextLine().trim());
            libraryService.returnBook(idInput,currentMember.getId());

        } catch (RuntimeException e) {
            throw new RuntimeException("Wrong Id");
        }
    }

    private void handleBorrow(Scanner scanner,Member currentMember) {
        System.out.println("Provide book id: ");
        try {
            Long idInput = Long.parseLong(scanner.nextLine().trim());
            libraryService.borrowBook(idInput,currentMember.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Wrong Id");
        }
    }

    private void handleSearchByAuthor(Scanner scanner) {
        String authorInput = scanner.nextLine();

        List<Book> booksToFind = libraryService.getByAuthor(authorInput);
        if (booksToFind.isEmpty())
            System.out.println("No books found");
        else {
            for (Book b : booksToFind)
                printBook(b);
        }

    }

    private void handleListAll() {
        if (libraryService.getAllBooks().isEmpty())
            System.out.println("No books in the Library");

        for (Book b : libraryService.getAllBooks())
            printBook(b);
    }

    private void handleAddBook(Scanner scanner) {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Auth: ");
        String auth = scanner.nextLine();
        System.out.println("isbn");
        String isbn = scanner.nextLine();

        System.out.println(libraryService.addBook(title, auth, isbn));
    }

    private void printMenu() {
        System.out.println("\n=== LIBRARY MANAGER ===");
        System.out.println(libraryService.getStats());
        System.out.println("-----------------------");
        System.out.println("1. Dodaj książkę");
        System.out.println("2. Wypisz wszystkie");
        System.out.println("3. Szukaj po autorze");
        System.out.println("4. Wypożycz (podaj ID)");
        System.out.println("5. Zwróć (podaj ID)");
        System.out.println("6. Pokaż dostępne");
        System.out.println("7. Usuń (podaj ID)");
        System.out.println("8. Szukaj Wszedzie");
        System.out.println("9. Pokaz Historie");
        System.out.println("0. Wyjście");
    }
    private void printLoginMenu(){
        System.out.println("\n=== LOGIN MANAGER ===");
        System.out.println("-----------------------");
        System.out.println("1. LogIn");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
    private void printBook(Book b) {
        System.out.printf("[%d] %s — %s | ISBN: %s | %s%n",
                b.getId(),
                b.getTitle(),
                b.getAuthor(),
                b.getIsbn() != null ? b.getIsbn() : "brak",
                b.isAvailable() ? "✓ dostępna" : "✗ wypożyczona");
    }
}
