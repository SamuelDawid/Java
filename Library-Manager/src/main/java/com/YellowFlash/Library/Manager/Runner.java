package com.YellowFlash.Library.Manager;

import com.YellowFlash.Library.Manager.model.Book;
import com.YellowFlash.Library.Manager.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    final LibraryService libraryService;
    final Scanner scanner;

    @Autowired
    public Runner(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);

    }


    @Override
    public void run(String... args) {
        seedData();
        showMenu();
    }

    private void seedData() {

        libraryService.addBook("Effective Java", "Joshua Bloch", "EFJB1");
        libraryService.addBook("Clean Code", "Robert Martin", "CCRM1");
        libraryService.addBook("Design Patterns", "Gang of Four", "DPGF1");
        libraryService.addBook("Spring in Action", "Craig Walls", "SIA01");
        libraryService.addBook("The Pragmatic Programmer", "Hunt and Thomas", "TPP01");

// dodaj 5 przykładowych książek żeby było na czym pracować

    }

    private void showMenu() {
        while (true) {
            printMenu();
            System.out.print("Wybierz: ");
            String choice = scanner.nextLine().trim();
            System.out.println();
            switch (choice) {
                case "1" -> handleAddBook(scanner);
                case "2" -> handleListAll();
                case "3" -> handleSearchByAuthor(scanner);
                case "4" -> handleBorrow(scanner);
                case "5" -> handleReturn(scanner);
                case "6" -> handleAvailable();
                case "7" -> handleDelete();
                case "0" -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
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
            System.out.println(b);

    }

    private void handleReturn(Scanner scanner) {
        System.out.println("Provide book id: ");
        try{
            Long idInput = Long.parseLong(scanner.nextLine().trim());
            libraryService.returnBook(idInput);
        } catch (RuntimeException e) {
            throw new RuntimeException("Wrong Id");
        }
    }

    private void handleBorrow(Scanner scanner) {
        System.out.println("Provide book id: ");
        try{
            Long idInput = Long.parseLong(scanner.nextLine().trim());
            libraryService.borrowBook(idInput);
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
                System.out.println(b);
        }

    }

    private void handleListAll() {
        if (libraryService.getAllBooks().isEmpty())
            System.out.println("No books in the Library");

        for (Book b : libraryService.getAllBooks())
            System.out.println(b);
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
        System.out.println("0. Wyjście");
    }

}
