package service;

import enums.Category;
import enums.Months;
import model.Transaction;

import java.util.ArrayList;

public class TransactionService {


    public static void setTransactionID(int transactionID) {
        TransactionService.transactionID = transactionID;
    }

    static int transactionID = 1;

    public static int getTransactionID() {
        return transactionID;
    }


    public ArrayList<Transaction> getTransactionsByUser(String userId,ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsByID = new ArrayList<>();
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId)) {
                transactionsByID.set(index, transaction);
                index++;
            }
        }
        return transactionsByID;
    }

    public ArrayList<Transaction> getTransactionsByMonth(String userId, Months month,ArrayList<Transaction> transactions) {
        ArrayList<Transaction> transactionsByMonth = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId)) {
                String[] split = transaction.getDate().split("-");
                if (split[1].equals(month.getNumber())) {
                    transactionsByMonth.add(transaction);
                }
            }
        }
        return transactionsByMonth;
    }
    public ArrayList<Transaction> getTransactionsByCategory(String userId, Category cat,ArrayList<Transaction> transactions){
        ArrayList<Transaction> transactionsByCategory = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId) && transaction.getCategory().equals(cat)) {

                transactionsByCategory.add(transaction) ;
            }
        }
        return transactionsByCategory;

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
    public void deleteTransaction(String transactionId,ArrayList<Transaction> transactions){
    int index = 0;
    for(int i= 0;i < transactions.size();i++){
        if(transactions.get(i).getUserId().equals(transactionId))
            index = i;

    }
    transactions.remove(index);
    }

}
