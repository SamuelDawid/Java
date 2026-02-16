package ui;

public class MenuManager {

    //Methods:
    public void displayMainMenu() {
        System.out.println("Main Menu");
        System.out.println("""
                1. Add Transaction, \s
                2. View Transactions,\s
                3. Set Budget,\s
                4. View Reports,\s
                5. Save & Exit.""");
    }

    public void displayTransactionMenu() {
        System.out.println("=== Add Transaction ===");
        System.out.println("""
                1. Income \s
                2. Expense \s
                Select type:
                """);

    }

    public void displayIncomeMenu() {
        System.out.println("=== Income Type ===");
        System.out.println("""
                1. Salary \s
                2. Gift \s
                3. Bonus \s
                Select type:
                """);

    }

    public void displayExpenseMenu() {
        System.out.println("=== Type ===");
        System.out.println("""
                1. Food \s
                2. Transport \s
                3. Entertainment \s
                4. Bills \s
                5. Shopping \s
                6. Health \s
                7. Other \s
                Select type:
                """);
    }

    public void displayMonthsOfYear() {
        System.out.println("""
                1. January  | 7. July\s
                2. February | 8. August \s
                3. March    | 9. September\s
                4. April    | 10. October\s
                5. May      | 11. November\s
                6. June     | 12. December\s
                 type month:
                """);
    }

    public void displayBudgetMenu() {

        System.out.println("""
                === Budget Menu ===
                1. Set Category Budget
                2. View All Budgets
                3. Check Budget Status
                4. Back to Main Menu
                Choice:
                """);
    }

    public void displayReportMenu() {

        System.out.println("""
                === Budget Menu ===
                1. Generate Monthly Report
                2. Print Category Breakdown
                3. Print Year To Date Summary
                4. Back to Main Menu
                Choice:
                """);
    }

    public void displayUserMenu() {
        System.out.println("=== Select or Add User ===");
        System.out.println("""
                1.Select Existing User, \s
                2.Add New User
                """);
    }

}
