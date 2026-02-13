package service;

import enums.Category;
import enums.Months;
import model.Transaction;

public class TransactionService {

     int transactionCount = 0;
     static int transactionID = 1;

    public static int getTransactionID() {
        return transactionID;
    }

    public void addTransaction(Transaction t, Transaction[] transactions) {
        transactionID++;
        t.setTransactionId(transactionID);
        transactionID++;

        transactions[transactionCount] = t;
        transactionCount++;

    }

    public Transaction[] getTransactionsByUser(String userId,Transaction[] transactions) {
        Transaction[] transactionsByID = new Transaction[transactions.length];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId)) {
                transactionsByID[index] = transaction;
                index++;
            }
        }
        return transactionsByID;
    }

    public Transaction[] getTransactionsByMonth(String userId, Months month,Transaction[] transactions) {
        Transaction[] transactionsByMonth = new Transaction[transactions.length];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId)) {
                String[] split = transaction.getDate().split("-");
                if (split[1].equals(month.getNumber())) {
                    transactionsByMonth[index] = transaction;
                    index++;
                }
            }
        }
        return transactionsByMonth;
    }
    public Transaction[] getTransactionsByCategory(String userId, Category cat,Transaction[] transactions){
        Transaction[] transactionsByCategory = new Transaction[transactions.length];
        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getUserId().equals(userId) && transaction.getCategory().equals(cat)) {

                transactionsByCategory[index] = transaction;
                index++;
            }
        }
        return transactionsByCategory;

    }
    public double calculateTotal(Transaction[] transactions){
        double sum = 0;
        for(Transaction t : transactions){
            if(t != null) {
                sum+= t.getAmount();
            }
        }
        return sum;
    }
    public void deleteTransaction(String transactionId,Transaction[] transactions){
    int index = 0;
    for(int i= 0;i < transactions.length;i++){
        if(transactions[i].getUserId().equals(transactionId))
            index = i;

    }

    for(int i = index; i < transactionCount;i++){
        transactions[i] = transactions[i+1];
    }

    transactionCount--;
    }



    public int getTransactionCount() {
        return transactionCount;
    }

}
