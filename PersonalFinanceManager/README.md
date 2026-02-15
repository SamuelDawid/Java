#Personal Finance Manager

Project Goal
Build a comprehensive Personal Finance Manager that tracks income, expenses, budgets, and financial goals. This will be a console-based application that demonstrates professional Java design patterns while staying 100% within your learned topics.

📋 Core Requirements
What the System Must Do:

User Management

Create and manage multiple user profiles
Each user has personal financial data


Transaction System

Record income (salary, bonuses, gifts)
Record expenses (categories: food, transport, entertainment, bills)
Track transaction dates and descriptions


Budget Management

Set monthly budgets per category
Track spending against budgets
Alert when approaching/exceeding limits


Financial Reports

Monthly summaries
Category-wise breakdowns
Savings calculations
Year-to-date statistics


Data Persistence

Save all data to text files
Load data on startup




🔧 Technical Constraints (Your Allowed Tools)
✅ You CAN Use:

Classes, objects, constructors
Encapsulation (private fields, public getters/setters)
Static methods/fields
Inheritance (extends)
Method overriding (@Override)
Enums (TransactionType, Category)
Arrays
Strings and StringBuilder
Primitives (int, double, boolean, etc.)
Control flow (if/else, switch, for, while)
Scanner for input
File I/O with basic text files
String formatting (printf, String.format)
this and super keywords
equals(), hashCode(), toString()

❌ You CANNOT Use (Not Learned Yet):

Collections (ArrayList, HashMap, etc.)
Interfaces
Abstract classes
Exception handling (try-catch)
Generics
Lambda expressions
Streams
Date/Time API (use String for dates)


🏗️ Suggested Class Structure
Package: model (Data Classes)
1. User.java
Fields:
- String userId
- String firstName  
- String lastName
- String email

Methods:
- Constructor
- Getters/Setters
- equals(), hashCode(), toString()
2. Transaction.java
Fields:
- String transactionId
- TransactionType type (enum: INCOME, EXPENSE)
- Category category (enum)
- double amount
- String date (format: "YYYY-MM-DD")
- String description
- String userId

Methods:
- Constructor
- Getters/Setters
- toString()
3. Budget.java
Fields:
- String budgetId
- String userId
- Category category
- double monthlyLimit
- String month (format: "YYYY-MM")

Methods:
- Constructor
- Getters/Setters
- calculateRemaining(double spent)
4. MonthlyReport.java
Fields:
- String userId
- String month
- double totalIncome
- double totalExpenses
- double savings

Methods:
- Constructor
- Getters/Setters
- calculateSavingsPercentage()
- toString() - formatted report
Package: enums
5. TransactionType.java (enum)
Values: INCOME, EXPENSE
6. Category.java (enum)
Values: SALARY, BONUS, GIFT, FOOD, TRANSPORT, ENTERTAINMENT, BILLS, SHOPPING, HEALTH, OTHER

Fields for each:
- String displayName
- TransactionType type

Constructor to set display name and type
Package: service (Business Logic)
7. TransactionService.java
Fields:
- Transaction[] transactions (fixed size, e.g., 1000)
- int transactionCount

Methods:
- addTransaction(Transaction t)
- Transaction[] getTransactionsByUser(String userId)
- Transaction[] getTransactionsByMonth(String userId, String month)
- Transaction[] getTransactionsByCategory(String userId, Category cat)
- double calculateTotal(Transaction[] transactions)
- void deleteTransaction(String transactionId)
8. BudgetService.java
Fields:
- Budget[] budgets (fixed size)
- int budgetCount

Methods:
- setBudget(Budget b)
- Budget getBudget(String userId, Category cat, String month)
- boolean isOverBudget(String userId, Category cat, String month, double spent)
- double calculateRemainingBudget(Budget b, double spent)
9. ReportService.java
Methods:
- MonthlyReport generateMonthlyReport(String userId, String month, Transaction[] trans)
- void printCategoryBreakdown(String userId, String month, Transaction[] trans)
- void printYearToDateSummary(String userId, String year, Transaction[] trans)
10. FileService.java
Methods:
- void saveUsers(User[] users, int count, String filename)
- User[] loadUsers(String filename, int[] count)
- void saveTransactions(Transaction[] trans, int count, String filename)
- Transaction[] loadTransactions(String filename, int[] count)
- void saveBudgets(Budget[] budgets, int count, String filename)
- Budget[] loadBudgets(String filename, int[] count)
Package: ui
11. MenuManager.java
Methods:
- static void displayMainMenu()
- static void displayTransactionMenu()
- static void displayBudgetMenu()
- static void displayReportMenu()
12. InputValidator.java
Static methods:
- boolean isValidEmail(String email)
- boolean isValidDate(String date)
- boolean isValidAmount(double amount)
- Category selectCategory(Scanner scanner)
Package: app
13. FinanceApp.java
main() method:
- Initialize services
- Load data from files
- Display menu
- Handle user choices
- Save data on exit

🔄 Data Flow Explanation

Startup:

FileService loads users, transactions, budgets from text files
Services are initialized with loaded data


Add Transaction:

User selects add transaction
InputValidator ensures valid category, amount, date
TransactionService adds to array
FileService saves to file


View Report:

User selects month
TransactionService filters transactions by month
ReportService calculates totals by category
BudgetService compares spending vs budgets
Formatted report displayed via printf()


Set Budget:

User inputs category and amount
BudgetService stores in array
FileService persists to file




⚠️ Edge Cases to Handle

Array Full: Check transactionCount before adding
No Transactions: Handle empty arrays gracefully
Invalid Input: Use InputValidator before processing
Duplicate IDs: Generate unique IDs (timestamp-based)
File Not Found: Create new file with empty data
Division by Zero: Check before calculating percentages
Null References: Initialize arrays, check before use
