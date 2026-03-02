package ui;

import enums.Category;

import java.time.Month;

public class MenuManager {

    //Methods:
    public void displayMainMenu() {
        System.out.println("""
                === Main Menu ===
                1. Add Transaction, \s
                2. View Transactions options,\s
                3. View Budget options,\s
                4. View Reports,\s
                5. Save & Exit.""");
    }


    public void displayAllTransactionsCategoryMenu() {
        System.out.println("=== Transaction Type ===");
        for (Category cat : Category.values())
            System.out.println(cat);

    }

    public void displayMonthsOfYear() {
        for (Month month : Month.values())
            System.out.println(month);
    }

    public void displayBudgetMenu() {

        System.out.println("""
                === Budget Menu ===
                1. Set Category Budget
                2. View All Budgets
                3. Check Budget Status
                4. Delete Budget
                5. Back to Main Menu
                Choice:
                """);
    }

    public void displayReportMenu() {
        System.out.println("""
                === Report Menu ===
                1. Generate Monthly Report
                2. Print Category Breakdown
                3. Print Year To Date Summary
                4. Back to Main Menu
                Choice:
                """);
    }

    public void displayTransactionsMenu() {
        System.out.println("""
                === Transactions Menu ===
                1.View all transactions, \s
                2.View only FOOD transactions \s
                3.View only transactions over $50\s
                4.View only transactions from specific month\s
                5.View only transactions from last 3 months\s
                """);
    }

    public void displayLoginMenu() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│       FINANCE MANAGER       │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│  1. Login                   │");
        System.out.println("│  2. Register                │");
        System.out.println("│  3. Exit                    │");
        System.out.println("└─────────────────────────────┘");
        System.out.print("  Select: ");
        ;
    }

}
