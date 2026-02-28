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
        for(Month month : Month.values())
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

    public void displayUserMenu() {
        System.out.println("""
                === Select or Add User ===
                1.Select Existing User, \s
                2.Add New User
                """);
    }
    public void displayTransactionsMenu(){
        System.out.println("""
                === Transactions Menu ===
                1.View all transactions, \s
                2.View only FOOD transactions \s
                3.View only transactions over $50
                4.View only transactions from specific month
                5.View only transactions from last 3 months
                """);
    }

}
