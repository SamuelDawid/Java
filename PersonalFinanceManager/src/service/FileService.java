package service;

import enums.Category;
import enums.Months;
import enums.TransactionType;
import model.Budget;
import model.Transaction;
import model.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class FileService {

     public void saveUsers(User[] users, int count, String filename) {
         try {
             PrintWriter writer = new PrintWriter(filename);
             for(int i = 0; i < count;i++){
                 writer.println(users[i].getUserId() + "," + users[i].getFirstName()+ ","+users[i].getLastName()+ ","+users[i].getEmail());
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }

     }
    public User[] loadUsers(String filename, int[] count){
         User[] loadUsers = new User[10];
        try (Scanner scanner = new Scanner(new File(filename))){
            // i have to split data into array
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] parts = data.split(",");
                User newUser = new User(parts[0],parts[1],parts[2],parts[3]);
                loadUsers[count[0]] = newUser;
                count[0]++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         return loadUsers;
     }
    public void saveTransactions(Transaction[] trans, int count, String filename){
         try {
             PrintWriter writer = new PrintWriter(filename);
             for(int i = 0; i < count;i++){
                 writer.println(trans[i].getTransactionId() + "," + trans[i].getDescription() + ","+ trans[i].getUserId() + ","+ trans[i].getType() + ","+ trans[i].getCategory() + ","+ trans[i].getAmount() + ","+ trans[i].getDate() + ",");
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
     }
    public Transaction[] loadTransactions(String filename, int[] count){
         Transaction[] transactionsToLoad = new Transaction[100];

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
                 transactionsToLoad[count[0]] = newTransaction;
                 count[0]++;
             }

         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         return  transactionsToLoad;
     }
    public void saveBudgets(Budget[] budgets, int count, String filename){
         try {
             PrintWriter writer = new PrintWriter(filename);
             for(int i = 0; i < count;i++){
                 writer.println(budgets[i].getBudgetId() + "," + budgets[i].getUserId() + "," + budgets[i].getMonth() + "," +budgets[i].getCategory() + "," + budgets[i].getMonthlyLimit());
             }
             writer.close();
         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
     }
    public Budget[] loadBudgets(String filename, int[] count){
         Budget[] budgetsToLoad = new Budget[100];
         try (Scanner scanner = new Scanner(new File(filename))){
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] parts = data.split(",");

                Budget newBudget = new Budget(parts[0],parts[1],
                        Months.valueOf(parts[2]),Category.valueOf(parts[3]),Double.parseDouble(parts[4]));
                budgetsToLoad[count[0]] = newBudget;
                count[0]++;
            }

         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
         }
         return  budgetsToLoad;
     }
}
