package service;

import enums.Category;
import enums.TransactionType;
import model.Budget;
import model.Transaction;
import model.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileService {

     public void saveUsers(ArrayList<User> users, String filename) {
         try {
             PrintWriter writer = new PrintWriter(filename);
             for (User user : users) {
                 writer.println(user.getUserId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getEmail());
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }

     }
     public ArrayList<User> loadUsers(String filename){
        ArrayList<User> loadUsers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))){
            // i have to split data into array
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] parts = data.split(",");
                User newUser = new User(parts[0],parts[1],parts[2],parts[3]);
                loadUsers.add(newUser);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         return loadUsers;
     }
    public void saveTransactions(ArrayList<Transaction> trans, String filename){
         try {
             PrintWriter writer = new PrintWriter(filename);
             for(Transaction t : trans){
                 writer.println(t.getTransactionId() + "," + t.getDescription() + ","+ t.getUserId() + ","+ t.getType() +
                         ","+ t.getCategory() + ","+ t.getAmount() + ","+ t.getDate());
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
     }
    public ArrayList<Transaction> loadTransactions(String filename){
        ArrayList<Transaction> transactionsToLoad = new ArrayList<>();

         try (Scanner scanner = new Scanner(new File(filename))){
             while (scanner.hasNextLine()){
                 String data = scanner.nextLine();
                 String[] parts = data.split(",");
                 Transaction newTransaction = new Transaction(
                         Integer.parseInt(parts[0]),
                         parts[1],parts[2],
                         TransactionType.valueOf(parts[3]),
                         Category.valueOf(parts[4]),
                         Double.parseDouble(parts[5]),
                         parts[6]);
                 transactionsToLoad.add(newTransaction);
             }

         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         return  transactionsToLoad;
     }
    public void saveBudgets(ArrayList<Budget> budgets,String filename){
         try {
             PrintWriter writer = new PrintWriter(filename);
             for(Budget b : budgets){
                 writer.println(b.getBudgetId() + "," + b.getUserId() + "," + b.getMonth() + "," +b.getCategory()
                         + "," + b.getMonthlyLimit());
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
     }
    public ArrayList<Budget> loadBudgets(String filename){
        ArrayList<Budget> budgetsToLoad = new ArrayList<>();
         try (Scanner scanner = new Scanner(new File(filename))){
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] parts = data.split(",");

                Budget newBudget = new Budget(Integer.parseInt(parts[0]),parts[1],
                        Month.valueOf(parts[2]),Category.valueOf(parts[3]),Double.parseDouble(parts[4]));
                budgetsToLoad.add(newBudget);
            }

         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         return  budgetsToLoad;
     }
}
