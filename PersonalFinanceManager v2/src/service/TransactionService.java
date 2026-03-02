package service;

import Iterators.TransactionIterator;
import enums.Category;
import enums.TransactionType;
import model.Budget;
import model.Transaction;
import model.User;
import ui.MenuManager;
import java.util.function.Function;

import java.util.*;
import java.util.function.Predicate;

public class TransactionService {


    public static void setTransactionID(int transactionID) {
        TransactionService.transactionID = transactionID;
    }

    static int transactionID = 1;
    static HashSet<Integer> trackTransactionID = new HashSet<>();
    private List<Transaction> allTransactions = new ArrayList<>();
    MenuManager ui = new MenuManager();
    Scanner scanner = new Scanner(System.in);
    //Predicate fields
    public static Predicate<Transaction> isExpense = transaction -> transaction.getType().equals(TransactionType.EXPENSE);
    public static Predicate<Transaction> isIncome  = transaction -> transaction.getType().equals(TransactionType.INCOME);
    public static Predicate<Transaction> isOverAmount (double amount) {
            return transaction -> transaction.getAmount() > amount;
    }  ;
    //Function transformations:

    public Function<Transaction,String> extractCategoryName = transaction -> transaction.getCategory().getDisplayName();
    public Function<Transaction,Double> extractAmount = Transaction::getAmount;
    public Function<Transaction,String> formatTransactionCSV = Transaction::formatTransactionCSV;


    public List<Transaction> getFilteredTransaction( Predicate<Transaction> filter){
        List<Transaction> transactionsToReturn = new ArrayList<>();
        TransactionIterator iterator = new TransactionIterator(allTransactions,filter);
        while (iterator.hasNext()) transactionsToReturn.add(iterator.next());
        return transactionsToReturn;
    }
    public void displayFilteredTransactions(List<Transaction> transactions, Predicate<Transaction> filter){
        TransactionIterator iterator = new TransactionIterator(transactions,filter);
        while (iterator.hasNext()) System.out.println(iterator.next());
    }
    public double calculateTotal(ArrayList<Transaction> transactions){
        double sum = 0;
        for(Transaction t : transactions){
            if(t != null) {
                sum+= t.getAmount();
            }
        }
        return sum;
    }

    public void addTransaction(User currentUser){
        ui.displayAllTransactionsCategoryMenu();
        String inputTransactionCategory = scanner.nextLine();

        System.out.println("Amount:");
        String choseAmount = scanner.nextLine();

        System.out.println("Date (YYYY-MM-DD):");
        String choseDate = scanner.nextLine();

        System.out.println("Description:");
        String choseDescription = scanner.nextLine();
        allTransactions.add(
                new Transaction(generateTransactionID(),
                        choseDescription,
                        currentUser.getUserId(),
                        TransactionType.INCOME,
                        Category.values()[Integer.parseInt(inputTransactionCategory) - 1],
                        Double.parseDouble(choseAmount),
                        choseDate)
        );
    }

    public int generateTransactionID(){
        while (true){
            var rnd = rndTransGen();
            if(!trackTransactionID.contains(rnd)){
               trackTransactionID.add(rnd);
               return rnd;
            }
        }
    }
    int rndTransGen(){
        return new Random().nextInt(100000000,999999999);
    }
    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }

}
