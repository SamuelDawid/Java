package model;

import enums.Months;

public class MonthlyReport {
    String userId;
    Months month;
    double totalIncome,totalExpenses,savings;

    public MonthlyReport(String userId, Months month, double totalIncome, double totalExpenses, double savings) {
        this.userId = userId;
        this.month = month;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.savings = savings;
    }
    void calculateSavingsPercentage(){
        System.out.println("SAVINGS: " + savings + "("+(savings * 100) / totalIncome+" of income)");
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "userId='" + userId + '\'' +
                ", month='" + month + '\'' +
                ", totalIncome=" + totalIncome +
                ", totalExpenses=" + totalExpenses +
                ", savings=" + savings +
                '}';
    }
//region Getters&Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Months getMonth() {
        return month;
    }

    public void setMonth(Months month) {
        this.month = month;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }
    //endregion
}
