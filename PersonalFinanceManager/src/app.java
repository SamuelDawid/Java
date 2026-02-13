import enums.Category;
import enums.Months;
import enums.TransactionType;
import model.Budget;
import model.MonthlyReport;
import model.Transaction;
import model.User;
import service.BudgetService;
import service.FileService;
import service.ReportService;
import service.TransactionService;
import ui.MenuManager;

import java.util.Scanner;


public class app {
    static final int EXPENSE_COUNT = 7;


    public static void main(String[] args) {
        User currentUser = new User();
        int currentUsersNumber = 0;
        //main() method:
        //- Initialize services
        Scanner scanner = new Scanner(System.in);

        MenuManager UI = new MenuManager();
        BudgetService budgetService = new BudgetService();
        TransactionService transactionService = new TransactionService();
        ReportService reportService = new ReportService();
        FileService fileService = new FileService();
        //- Load data from files
        User[] allUsers = new User[100];
        int[] userCount = new int[1];
        allUsers = fileService.loadUsers("data/users.txt",userCount);

        Transaction[] allTransactions = new Transaction[100];
        int[] transCount = new int[1];
        allTransactions = fileService.loadTransactions("data/transactions.txt",transCount);

        Budget[] allBudgets = new Budget[100];
        int[] budgetsCount = new int[1];
        allBudgets = fileService.loadBudgets("data/bud.txt",budgetsCount);
        //region UserHandling
        boolean userFound = false;
        while (!userFound) {
            UI.displayUserMenu();
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    System.out.println("provide userID");
                    String userIDinput = scanner.nextLine();

                    for (User u : allUsers) {
                        if (u != null && u.getUserId().equals(userIDinput)) {
                            currentUser = u;
                            userFound = true;
                            System.out.println("User logged in.");
                            break;
                        }
                    }
                    if(!userFound)
                    System.out.println("No user found!");
                    break;
                case "2":

                    System.out.println("Adding new User");
                    System.out.println("First Name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Last Name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();

                    allUsers[currentUsersNumber] = new User(String.valueOf(currentUsersNumber + 100), firstName, lastName, email);
                    System.out.println("Added : " + allUsers[currentUsersNumber]);
                    currentUser = allUsers[currentUsersNumber];
                    currentUsersNumber++;

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
            UI.displayMainMenu();
            String input = scanner.nextLine();
            //  1. Add Transaction,
            //  2. View Transactions,\s
            //  3. Set Budget,\s
            //  4. View Reports,\s
            //  5. Save & Exit.

            switch (input) {
                //region TransactionRegion
                case "1":
                    UI.displayTransactionMenu();
                    String inputTransactionMenu = scanner.nextLine();
                    if (!inputTransactionMenu.isEmpty()) {
                        if (inputTransactionMenu.equalsIgnoreCase("income") || inputTransactionMenu.equals("1")) {
                            // add income transaction.
                            if (!currentUser.getUserId().isEmpty()) {
                                UI.displayIncomeMenu();
                                String choseIncomeType = scanner.nextLine();

                                System.out.println("Amount:");
                                String choseAmount = scanner.nextLine();

                                System.out.println("Date (YYYY-MM-DD):");
                                String choseDate = scanner.nextLine();

                                System.out.println("Description:");
                                String choseDescription = scanner.nextLine();

                                Transaction newTransaction =
                                        new Transaction(TransactionService.getTransactionID(),
                                                choseDescription,
                                                currentUser.getUserId(),
                                                TransactionType.INCOME,
                                                Category.values()[Integer.parseInt(choseIncomeType) - 1],
                                                Double.parseDouble(choseAmount),
                                                choseDate);
                                transactionService.addTransaction(newTransaction,allTransactions);
                            } else {
                                System.out.println("No current user logged in!");
                                return;
                            }


                        } else if (inputTransactionMenu.equalsIgnoreCase("expense") || inputTransactionMenu.equals("2")) {
                            if (!currentUser.getUserId().isEmpty()) {
                                UI.displayExpenseMenu();
                                String choseIncomeType = scanner.nextLine();

                                System.out.println("Amount:");
                                String choseAmount = scanner.nextLine();

                                System.out.println("Date (DD-MM-YYYY):");
                                String choseDate = scanner.nextLine();

                                System.out.println("Description:");
                                String choseDescription = scanner.nextLine();

                                Transaction newTransaction =
                                        new Transaction(TransactionService.getTransactionID(),
                                                choseDescription,
                                                currentUser.getUserId(),
                                                TransactionType.EXPENSE,
                                                Category.values()[Integer.parseInt(choseIncomeType) + 2],
                                                Double.parseDouble(choseAmount),
                                                choseDate);
                                transactionService.addTransaction(newTransaction,allTransactions);
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
                    Transaction[] transactionsToDisplay = transactionService.getTransactionsByUser(currentUser.getUserId(),allTransactions);
                    for (Transaction t : transactionsToDisplay)
                        if (t != null)
                            System.out.println(t);
                    if(transactionsToDisplay.length == 0)
                        System.out.println("No transactions yet");
                    break;
                //endregion
                //region BudgetRegion
                case "3":
                    UI.displayBudgetMenu();
                    String budgetMenuChoice = scanner.nextLine();

                    switch (budgetMenuChoice) {
                        case "1":
                            //Set Category Budget
                            UI.displayExpenseMenu();
                            String budgetTypeInput = scanner.nextLine();

                            UI.displayMonthsOfYear();
                            String monthTypeInput = scanner.nextLine();

                            System.out.println("Monthly budget: ");
                            String monthlyBudgetInput = scanner.nextLine();

                            Budget newBudget = new Budget("0",
                                    currentUser.getUserId(),
                                    Months.values()[Integer.parseInt(monthTypeInput)],
                                    Category.values()[Integer.parseInt(budgetTypeInput) + 2],
                                    Double.parseDouble(monthlyBudgetInput)
                            );
                            budgetService.setBudget(newBudget,allBudgets);

                            break;
                        case "2":
                            //View All Budgets
                            for (Budget b : allBudgets) {
                                if (b != null && b.getUserId().equals(currentUser.getUserId())) {
                                    System.out.println(b);
                                }
                            }
                            break;
                        case "3":
                            //Check Budget Status
                            System.out.println("Choose month: ");
                            UI.displayMonthsOfYear();
                            int month = scanner.nextInt();
                            System.out.println("=== Budget Status - " + Months.values()[month] + " 2026 ===");
                            //=== Budget Status - February 2026 ===
                            Budget[] budgetsToPrint = new Budget[EXPENSE_COUNT];
                            int budgetCount = 0;
                            for (Category c : Category.values()) {
                                if(c != null && c.getType() == TransactionType.EXPENSE){
                                budgetsToPrint[budgetCount] = budgetService.getBudget(
                                        currentUser.getUserId(), c, Months.values()[month],allBudgets
                                );
                                budgetCount++;
                            }}
                            //Loop through budgetsToPrint,
                            for (Budget b : budgetsToPrint) {
                                // for each non-null budget: get transactions for that category/month,
                                if (b != null) {
                                    Transaction[] transactionsTotal = transactionService.getTransactionsByMonth(
                                            currentUser.getUserId(),
                                            Months.values()[month],allTransactions);
                                    // calculate total spent,
                                    double totalSpent = transactionService.calculateTotal(transactionsTotal);
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
                            return;
                        default:
                            System.out.println("Incorrect choice");
                            break;
                    }


                    break;
                //endregion
                //region Reports
                case "4":
                    //Display report menu, get user choice,
                    UI.displayReportMenu();;
                    String userReportInput = scanner.nextLine();

                    switch (userReportInput){
                        //1. Generate Monthly Report
                        case "1":
                            System.out.println("Select which month");
                            UI.displayMonthsOfYear();
                            int monthInput = scanner.nextInt();
                            MonthlyReport newReport = reportService.generateMonthlyReport(
                                    currentUser.getUserId(),
                                    Months.values()[monthInput],
                                    allTransactions, transactionService
                            );
                            System.out.println(newReport);
                            break;
                        //                2. Print Category Breakdown
                        case "2":
                            reportService.printCategoryBreakdown(currentUser.getUserId(),allTransactions,transactionService);
                            break;
                        //                3. Print Year To Date Summary
                        case "3":
                            System.out.println("Type which year(YYYY)");
                            String year = scanner.nextLine();
                            reportService.printYearToDateSummary(currentUser.getUserId(), year,allTransactions);
                            break;
                        //                4. Back to Main Menu
                        case "4":
                            return;
                    }
                    break;
                    //endregion
                case "5":
                    fileService.saveUsers(allUsers,userCount[0],"data/users.txt");
                    fileService.saveTransactions(allTransactions,transCount[0],"data/transactions.txt");
                    fileService.saveBudgets(allBudgets,budgetsCount[0],"data/bud.txt");
                    return;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }

        }
        //- Handle user choices
        //- Save data on exit
    }
}
