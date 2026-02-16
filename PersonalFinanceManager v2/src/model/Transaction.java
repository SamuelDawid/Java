package model;

import enums.Category;
import enums.TransactionType;

public class Transaction {
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
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", category=" + category +
                ", amount=" + amount +
                ", date(format: DD-MM-YYYY)='" + date + '\'' +
                '}';
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
}
