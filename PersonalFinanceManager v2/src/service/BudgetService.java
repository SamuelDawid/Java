package service;

import Iterators.BudgetIterator;
import enums.Category;
import enums.TransactionType;
import model.Budget;
import model.Transaction;
import model.User;
import org.jetbrains.annotations.NotNull;
import ui.MenuManager;

import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class BudgetService {
    //Fields:
    static int budgetID;
    static HashSet<Integer> trackBudgetID = new HashSet<>();
    private List<Budget> allBudgets = new ArrayList<>();
    MenuManager ui = new MenuManager();
    Scanner scanner = new Scanner(System.in);
    public Function<Budget,String> formatBudgetCSV = Budget::formatBudgetCSV;


    public void setBudget(User currentUser){
        for (Category category : Category.values())
            if(category.getType().equals(TransactionType.EXPENSE))
                System.out.println(category);
        String budgetTypeInput = scanner.nextLine();

        ui.displayMonthsOfYear();
        String monthTypeInput = scanner.nextLine();

        System.out.println("Monthly budget: ");
        String monthlyBudgetInput = scanner.nextLine();

        allBudgets.add( new Budget(generateBudgetID(),
                currentUser.getUserId(),
                Month.values()[Integer.parseInt(monthTypeInput )-1],
                Category.values()[Integer.parseInt(budgetTypeInput)],
                Double.parseDouble(monthlyBudgetInput)
        ));
    }
    public List<Budget> getFilteredBudgets(Predicate<Budget> predicate){
        List<Budget> listToReturn = new ArrayList<>();
        BudgetIterator iterator = new BudgetIterator(allBudgets,predicate);
        while (iterator.hasNext())
            listToReturn.add(iterator.next());
        return  listToReturn;
    }
    public boolean removeBudget(User current,int budgetID){
        Optional<Budget> remove = budgetToRemove(current,budgetID);

        if(remove.isPresent()){
            allBudgets.remove(remove.get());
            return true;
        }
        return false;
    }
    @org.jetbrains.annotations.NotNull
    private Optional<Budget> budgetToRemove(User current, int budgetID){
        List<Budget> userBudgets = getFilteredBudgets(budget -> budget.getUserId().equals(current.getUserId()));
        return  userBudgets.stream().filter(budget -> budget.getBudgetId() == budgetID).findAny();
    }
    public void displayFilteredBudgets(Predicate<Budget> predicate){
    BudgetIterator iterator = new BudgetIterator(allBudgets,predicate);
    while (iterator.hasNext())
        System.out.println(iterator.next());
    }
    public void displayBudgetStatus(@NotNull Budget budget, @NotNull TransactionService transactionService){
        // calculate total spend
        List<Transaction> filteredTransactions = transactionService.getFilteredTransaction(
                transaction -> transaction.userId().equals(budget.getUserId()) &&
                        transaction.category().equals(budget.getCategory()) &&
                        transaction.date().split("-")[1].equals(String.format("%02d",budget.getMonth().getValue()))
        );
        System.out.println(filteredTransactions);
        double totalSpent = transactionService.calculateTotal(filteredTransactions);
        double percentage = totalSpent * 100 / budget.getMonthlyLimit();
        System.out.println(budget.getCategory() + ": " + totalSpent + " / " + budget.getMonthlyLimit() +
                " (" + (int) percentage + "%)");
        if (percentage == 100)
            System.out.print("⚠ AT LIMIT");
        else if (percentage > 100)
            System.out.print("✗ OVER BUDGET");
        else
            System.out.print("✓");
    }
    private int generateBudgetID(){
        while (true){
            var rnd = new Random().nextInt(1000000,9999999);
            if(!trackBudgetID.contains(rnd)){
                trackBudgetID.add(rnd);
                return rnd;
            }
        }
    }

    public List<Budget> getAllBudgets() {
        return allBudgets;
    }
    public void setAllBudgets(List<Budget> allBudgets) {
        this.allBudgets = allBudgets;
    }
    public static int getBudgetID() {
        return budgetID;
    }
    public static void setBudgetID(int budgetID) {
        BudgetService.budgetID = budgetID;
    }
}
