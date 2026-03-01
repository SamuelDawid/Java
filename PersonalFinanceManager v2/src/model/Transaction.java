package model;

import enums.Category;
import enums.TransactionType;

public class Transaction implements Comparable<Transaction> {
    int transactionId;
    String description,userId;
    TransactionType type ;
    Category category ;
    double amount;
//-  (format: "YYYY-MM-DD")
    String date;
    public Transaction(){

    }

    public Transaction(int transactionId, String description, String userId, TransactionType type, Category category, double amount, String date) {
        this.transactionId = transactionId;
        this.description = description;
        this.userId = userId;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }



    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %.2f | %s",
                transactionId, date, type, category.getDisplayName(), amount, description);
    }
    public String formatTransactionCSV(){
        return String.format("%d,%s,%s,%s,%s,%.1f,%s",
                transactionId, description, userId, type, category, amount, date);
    }
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(amount,o.getAmount());
    }
}
