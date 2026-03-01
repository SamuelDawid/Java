package model;

import enums.Category;

import java.time.Month;

public class Budget {
    String  userId;
    int budgetId;
    Category category;
    Month month;
    double monthlyLimit;

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId='" + budgetId + '\'' +
                ", userId='" + userId + '\'' +
                ", month='" + month + '\'' +
                ", category=" + category +
                ", monthlyLimit=" + monthlyLimit +
                '}';
    }
    public String formatBudgetCSV(){
        return String.format("%s: $%.2f for %s",
                category, monthlyLimit, month);
    }
    public Budget(int budgetId, String userId, Month month, Category category, double monthlyLimit) {
        this.budgetId = budgetId;
        this.userId = userId;
        this.month = month;
        this.category = category;
        this.monthlyLimit = monthlyLimit;
    }
    public double calculateRemaining(double spent){
        return monthlyLimit - spent ;

    }
    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
    //region Setter&Getters
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }
    //endregion
}
