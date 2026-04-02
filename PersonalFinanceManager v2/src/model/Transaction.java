package model;

import enums.Category;
import enums.TransactionType;

import java.time.LocalDate;

public record Transaction(int transactionId, String description, String userId, TransactionType type, Category category,
                          double amount, LocalDate date) implements Comparable<Transaction> {

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %s | %.2f | %s",
                transactionId, date, type, category.getDisplayName(), amount, description);
    }

    public String formatTransactionCSV() {
        return String.format("%d,%s,%s,%s,%s,%.1f,%s",
                transactionId, description, userId, type, category, amount, date);
    }

    @Override
    public int compareTo(Transaction o) {
        return Double.compare(amount, o.amount());
    }
}
