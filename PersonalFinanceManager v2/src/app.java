//region imports

import enums.Category;
import enums.Months;
import enums.TransactionType;
import model.Budget;
import model.MonthlyReport;
import model.Transaction;
import model.User;
import service.*;
import ui.MenuManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Predicate;
//endregion

public class app {

    public static void main(String[] args) {

        //region InitializeServices
        InitializeServices allServices = getInitializeServices();
        //endregion
        //region LoadData
        LoadData loadAlldata = getLoadData(allServices);
        IntializeServices services = new IntializeServices(loadAlldata.allUsers(), loadAlldata.allTransactions(), loadAlldata.allBudgets());
        //endregion
        //region UserHandling
        boolean userFound = false;
        while (!userFound) {
            allServices.UI().displayUserMenu();
            String userInput = allServices.scanner().nextLine();

            switch (userInput) {
                case "1":
                    System.out.println("provide userID");
                    String userIDinput = allServices.scanner().nextLine();

                    for (User u : services.allUsers()) {
                        if (u != null && u.getUserId().equals(userIDinput)) {
                            allServices.userService.currentUser = u;
                            userFound = true;
                            System.out.println("User logged in.");
                            break;
                        }
                    }
                    if (!userFound)
                        System.out.println("No user found!");
                    break;
                case "2":
                    services.allUsers.add(allServices.userService.createNewUser());
                    System.out.println("Added : " + allServices.userService.currentUser);
                    userFound = true;
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
        //endregion
        //- Display menu
        while (true) {
            allServices.UI().displayMainMenu();
            String input = allServices.scanner().nextLine();

            switch (input) {
                //region TransactionRegion
                case "1":
                    allServices.UI().displayTransactionMenu();
                    String inputTransactionMenu = allServices.scanner().nextLine();
                    if (!inputTransactionMenu.isEmpty()) {
                        if (inputTransactionMenu.equalsIgnoreCase("income") || inputTransactionMenu.equals("1")) {
                            // add income transaction.
                            if (!allServices.userService.currentUser.getUserId().isEmpty()) {
                                allServices.UI().displayIncomeMenu();
                                String choseIncomeType = allServices.scanner().nextLine();

                                System.out.println("Amount:");
                                String choseAmount = allServices.scanner().nextLine();

                                System.out.println("Date (YYYY-MM-DD):");
                                String choseDate = allServices.scanner().nextLine();

                                System.out.println("Description:");
                                String choseDescription = allServices.scanner().nextLine();
                                Transaction newTransaction =
                                        new Transaction(allServices.transactionService().generateTransactionID(),
                                                choseDescription,
                                                allServices.userService.getUserId(),
                                                TransactionType.INCOME,
                                                Category.values()[Integer.parseInt(choseIncomeType) - 1],
                                                Double.parseDouble(choseAmount),
                                                choseDate);
                                services.allTransactions().add(newTransaction);
                                allServices.transactionService().sortedTransactions.add(newTransaction);
                                allServices.transactionService().sortedByDate.add(newTransaction);

                            } else {
                                System.out.println("No current user logged in!");
                                return;
                            }


                        } else if (inputTransactionMenu.equalsIgnoreCase("expense") || inputTransactionMenu.equals("2")) {
                            if (!allServices.userService.currentUser.getUserId().isEmpty()) {
                                allServices.UI().displayExpenseMenu();
                                String choseIncomeType = allServices.scanner().nextLine();

                                System.out.println("Amount:");
                                String choseAmount = allServices.scanner().nextLine();

                                System.out.println("Date (YYYY-MM-DD):");
                                String choseDate = allServices.scanner().nextLine();

                                System.out.println("Description:");
                                String choseDescription = allServices.scanner().nextLine();

                                Transaction newTransaction =
                                        new Transaction(allServices.transactionService().generateTransactionID(),
                                                choseDescription,
                                                allServices.userService.currentUser.getUserId(),
                                                TransactionType.EXPENSE,
                                                Category.values()[Integer.parseInt(choseIncomeType) + 2],
                                                Double.parseDouble(choseAmount),
                                                choseDate);
                                services.allTransactions().add(newTransaction);
                                allServices.transactionService().sortedTransactions.add(newTransaction);
                                allServices.transactionService().sortedByDate.add(newTransaction);


                            } else {
                                System.out.println("No current user logged in!");
                                return;
                            }
                        }
                    }
                    break;
                // endregion
                //region ViewTransactionRegion
                case "2":
                    allServices.UI.displayTransactionsMenu();
                    String transactionMenuChoice = allServices.scanner().nextLine();
                    ArrayList<Transaction> transactionsToDisplay = allServices.transactionService().getTransactionsByUser(
                            allServices.userService.currentUser.getUserId(), services.allTransactions());
                    switch (transactionMenuChoice) {
                        case "1":
                            for (Transaction t : transactionsToDisplay)
                                if (t != null)
                                    System.out.println(t);
                            if (transactionsToDisplay.isEmpty())
                                System.out.println("No transactions yet");
                            break;
                        case "2":
                            allServices.transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getCategory().equals(Category.FOOD));
                            break;
                        case "3":
                            allServices.transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getAmount() > 50);
                            break;
                        case "4":

                            allServices.UI.displayMonthsOfYear();
                            int transactionMenuSelectMonth = allServices.scanner().nextInt();
                            Months selectedMonth = Months.values()[transactionMenuSelectMonth - 1];
                            allServices.transactionService.displayFilteredTransactions(transactionsToDisplay,
                                    transaction -> transaction.getDate().split("-")[1].equals(selectedMonth.getNumber()));
                            break;
                        case "5":
                            allServices.transactionService.displayFilteredTransactions(transactionsToDisplay,
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
                    allServices.UI().displayBudgetMenu();
                    String budgetMenuChoice = allServices.scanner().nextLine();

                    switch (budgetMenuChoice) {
                        case "1":
                            //Set Category Budget
                            allServices.UI().displayExpenseMenu();
                            String budgetTypeInput = allServices.scanner().nextLine();

                            allServices.UI().displayMonthsOfYear();
                            String monthTypeInput = allServices.scanner().nextLine();

                            System.out.println("Monthly budget: ");
                            String monthlyBudgetInput = allServices.scanner().nextLine();

                            Budget newBudget = new Budget("0",
                                    allServices.userService.currentUser.getUserId(),
                                    Months.values()[Integer.parseInt(monthTypeInput)],
                                    Category.values()[Integer.parseInt(budgetTypeInput) + 2],
                                    Double.parseDouble(monthlyBudgetInput)
                            );
                            allServices.budgetService().setBudget(newBudget, services.allBudgets());

                            break;
                        case "2":
                            //View All Budgets
                            for (Budget b : services.allBudgets()) {
                                if (b != null && b.getUserId().equals(allServices.userService.currentUser.getUserId())) {
                                    System.out.println(b);
                                }
                            }
                            break;
                        case "3":
                            //Check Budget Status
                            System.out.println("Choose month: ");
                            allServices.UI().displayMonthsOfYear();
                            int month = allServices.scanner().nextInt();
                            System.out.println("=== Budget Status - " + Months.values()[month] + " 2026 ===");
                            //=== Budget Status - February 2026 ===
                            ArrayList<Budget> budgetsToPrint = new ArrayList<>();
                            for (Category c : Category.values()) {

                            }
                            //Loop through budgetsToPrint,
                            for (Budget b : budgetsToPrint) {
                                // for each non-null budget: get transactions for that category/month,
                                if (b != null) {
                                    ArrayList<Transaction> transactionsTotal = allServices.transactionService().getTransactionsByMonth(
                                            allServices.userService.currentUser.getUserId(),
                                            Months.values()[month], services.allTransactions());
                                    // calculate total spent,
                                    double totalSpent = allServices.transactionService().calculateTotal(transactionsTotal);
                                    double procentage = totalSpent * 100 / b.getMonthlyLimit();
                                    System.out.println(b.getCategory() + ": " + totalSpent + " / " + b.getMonthlyLimit() +
                                            " (" + (int) procentage + "%)");
                                    if (procentage == 100)
                                        System.out.print("⚠ AT LIMIT");
                                    else if (procentage > 100)
                                        System.out.print("✗ OVER BUDGET");
                                    else
                                        System.out.print("✓");
                                }
                            }
                            break;
                        case "4":
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
                    allServices.UI().displayReportMenu();
                    ;
                    String userReportInput = allServices.scanner().nextLine();

                    switch (userReportInput) {
                        //1. Generate Monthly Report
                        case "1":
                            System.out.println("Select which month");
                            allServices.UI().displayMonthsOfYear();
                            int monthInput = allServices.scanner().nextInt();
                            MonthlyReport newReport = allServices.reportService().generateMonthlyReport(
                                    allServices.userService.currentUser.getUserId(),
                                    Months.values()[monthInput],
                                    services.allTransactions()
                            );
                            System.out.println(newReport);
                            break;
                        //                2. Print Category Breakdown
                        case "2":
                            allServices.reportService().printCategoryBreakdown(allServices.userService.currentUser.getUserId(), services.allTransactions(), allServices.transactionService());
                            break;
                        //                3. Print Year To Date Summary
                        case "3":
                            System.out.println("Type which year(YYYY)");
                            String year = allServices.scanner().nextLine();
                            allServices.reportService().printYearToDateSummary(allServices.userService.currentUser.getUserId(), year, services.allTransactions());
                            break;
                        //                4. Back to Main Menu
                        case "4":
                            break;
                    }
                    break;
                //endregion
                case "5":
                    allServices.fileService().saveUsers(services.allUsers(), "data/users.txt");
                    allServices.fileService().saveTransactions(services.allTransactions(), "data/transactions.txt");
                    allServices.fileService().saveBudgets(services.allBudgets(), "data/bud.txt");
                    return;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }

        }
    }

    //region records
    private static LoadData getLoadData(InitializeServices allServices) {
        ArrayList<User> allUsers = allServices.fileService().loadUsers("data/users.txt");
        ArrayList<Transaction> allTransactions = allServices.fileService().loadTransactions("data/transactions.txt");
        ArrayList<Budget> allBudgets = allServices.fileService().loadBudgets("data/bud.txt");
        LoadData loadAlldata = new LoadData(allUsers, allTransactions, allBudgets);
        return loadAlldata;
    }

    private record LoadData(ArrayList<User> allUsers, ArrayList<Transaction> allTransactions,
                            ArrayList<Budget> allBudgets) {
    }

    private static InitializeServices getInitializeServices() {
        Scanner scanner = new Scanner(System.in);
        MenuManager UI = new MenuManager();
        BudgetService budgetService = new BudgetService();
        TransactionService transactionService = new TransactionService();
        ReportService reportService = new ReportService();
        FileService fileService = new FileService();
        UserService userService = new UserService();
        return new InitializeServices(scanner, UI, budgetService, transactionService, reportService, fileService, userService);
    }

    private record InitializeServices(Scanner scanner, MenuManager UI, BudgetService budgetService,
                                      TransactionService transactionService, ReportService reportService,
                                      FileService fileService, UserService userService) {
    }

    private record IntializeServices(ArrayList<User> allUsers, ArrayList<Transaction> allTransactions,
                                     ArrayList<Budget> allBudgets) {
    }
    //endregion
}
