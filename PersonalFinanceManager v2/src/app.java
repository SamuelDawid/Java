//region imports

import Iterators.TransactionIterator;
import enums.Category;
import model.Budget;
import model.MonthlyReport;
import model.Transaction;
import model.User;
import service.*;
import ui.MenuManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
//endregion

public class app {

    public static void main(String[] args) {

        //region Initialize
        Scanner scanner = new Scanner(System.in);
        MenuManager UI = new MenuManager();
        BudgetService budgetService = new BudgetService();
        TransactionService transactionService = new TransactionService();
        ReportService reportService = new ReportService();
        FileService fileService = new FileService();
        UserService userService = new UserService();
        //endregion
        //region LoadData
        userService.setAllUsers(fileService.loadUsers("data/users.txt"));
        transactionService.setAllTransactions(fileService.loadTransactions("data/transactions.txt"));
        budgetService.setAllBudgets(fileService.loadBudgets("data/bud.txt"));
        //endregion
        //region UserHandling
        boolean userFound = false;
        while (!userFound) {
            UI.displayLoginMenu();
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    if (userService.Login()) {
                        System.out.println("Logged IN!");
                        userFound = true;
                    }
                    break;
                case "2":
                    if (userService.signUp()) {
                        userFound = true;
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
        //endregion
        //- Display menu
        while (true) {
            UI.displayMainMenu();
            String input = scanner.nextLine();
            switch (input) {
                //region TransactionRegion
                case "1":
                    transactionService.addTransaction(userService.currentUser);
                    break;
                case "2":
                    UI.displayTransactionsMenu();
                    String transactionMenuChoice = scanner.nextLine();
                    List<Transaction> transactionsToDisplay = transactionService.getFilteredTransaction(
                            transaction -> transaction.getUserId().equals(userService.currentUser.getUserId())
                    );
                    switch (transactionMenuChoice) {
                        case "1":
                            for (Transaction t : transactionsToDisplay)
                                if (t != null)
                                    System.out.println(t);
                            if (transactionsToDisplay.isEmpty())
                                System.out.println("No transactions yet");
                            break;
                        case "2":
                            transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getCategory().equals(Category.FOOD));
                            break;
                        case "3":
                            transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getAmount() > 50);
                            break;
                        case "4":
                            UI.displayMonthsOfYear();
                            int transactionMenuSelectMonth = scanner.nextInt();
                            Month selectedMonth = Month.values()[transactionMenuSelectMonth - 1];
                            transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getDate().split("-")[1].equals(String.format("%02d", selectedMonth.getValue())));
                            break;
                        case "5":
                            transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction ->
                                            transaction.getDate().compareTo(LocalDate.now().minusMonths(3).toString()) >= 0
                                                    && transaction.getDate().compareTo(LocalDate.now().toString()) <= 0
                            );
                            break;
                    }
                    break;
                //endregion
                //region BudgetRegion
                case "3":
                    UI.displayBudgetMenu();
                    String budgetMenuChoice = scanner.nextLine();

                    switch (budgetMenuChoice) {
                        case "1":
                            //Set Category Budget
                            budgetService.setBudget(userService.currentUser);
                            break;
                        case "2":
                            //View All Budgets
                            budgetService.displayFilteredBudgets(budget -> budget.getUserId().equals(userService.currentUser.getUserId()));
                            break;
                        case "3":
                            System.out.println("Select Month: ");
                            UI.displayMonthsOfYear();
                            String userBudgetSelectMonth = scanner.nextLine();
                            Month month = Month.of(Integer.parseInt(userBudgetSelectMonth));
                            List<Budget> budgetsToCheck = budgetService.getFilteredBudgets(
                                    budget -> budget.getMonth().equals(month));
                            for (Budget b : budgetsToCheck)
                                budgetService.displayBudgetStatus(b, transactionService);
                            break;
                        case "4":
                            //remove budget
                            System.out.println("Provide budgetID: ");
                            String budgetIdInput = scanner.nextLine();
                            if(budgetService.removeBudget(userService.currentUser,Integer.parseInt(budgetIdInput)))

                            ;
                            break;
                        case "5":
                            // Back to Main Menu
                            break;
                        default:
                            System.out.println("Incorrect choice");
                            break;
                    }
                    break;
                //endregion
                //region Reports
                case "4":
                    //Display report menu, get user choice,
                    UI.displayReportMenu();
                    String userReportInput = scanner.nextLine();

                    switch (userReportInput) {
                        //1. Generate Monthly Report
                        case "1":
                            System.out.println("Select which month");
                            UI.displayMonthsOfYear();
                            int monthInput = scanner.nextInt();

                            MonthlyReport newReport = reportService.generateMonthlyReport(
                                    userService.currentUser.getUserId(),
                                    Month.values()[monthInput],
                                    new ArrayList<>(transactionService.getAllTransactions()
                                    ));
                            System.out.println(newReport);
                            scanner.nextLine();
                            break;
                        case "2":
                            reportService.printCategoryBreakdown(userService.currentUser.getUserId(), transactionService);
                            break;
                        case "3":
                            System.out.println("Type which year(YYYY)");
                            String year = scanner.nextLine();
                            reportService.printYearToDateSummary(userService.currentUser.getUserId(), year, new ArrayList<>(transactionService.getAllTransactions()));
                            break;
                        case "4":
                            break;
                    }
                    break;
                //endregion
                case "5":
                    fileService.saveGeneric(userService.getAllUsers(), user -> true, userService.userFormatCVS, "data/users.txt");
                    fileService.saveGeneric(transactionService.getAllTransactions(),
                            transaction -> true,
                            transactionService.formatTransactionCSV,
                            "data/transactions.txt");
                    fileService.saveGeneric(budgetService.getAllBudgets(),
                            budget -> true,
                            budgetService.formatBudgetCSV,
                            "data/bud.txt");
                    return;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }

        }
    }

}
