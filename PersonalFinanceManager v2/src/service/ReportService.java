package service;

import enums.Category;
import enums.TransactionType;
import model.MonthlyReport;
import model.Transaction;

import java.time.Month;
import java.util.ArrayList;

public class ReportService {
    //Methods:
     public MonthlyReport generateMonthlyReport(String userId, Month month, ArrayList<Transaction> trans){
        //Get all transactions for user,
         double totalIncome = 0,totalExpenses = 0;

        for (Transaction t : trans){
            if(t != null){
                if(t.getUserId().equals(userId) && t.getDate().split("-")[1].equals(String.valueOf(month.getValue()))){
                    // calculate totalExpenses (filter EXPENSE type),
                    if(t.getType() == TransactionType.EXPENSE){
                        totalExpenses+= t.getAmount();
                        // calculate totalIncome (filter INCOME type),
                    }else if(t.getType() == TransactionType.INCOME){
                        totalIncome+= t.getAmount();
                    }
                }

            }
        }

        return new MonthlyReport(userId,month,totalIncome,totalExpenses,(totalIncome - totalExpenses));
     }
    public void printCategoryBreakdown(String userId,TransactionService transactionService){

        for(Category c: Category.values()){
            ArrayList<Transaction> categoryTrans = (ArrayList<Transaction>) transactionService.getFilteredTransaction(
                    transaction -> transaction.getCategory().equals(c) &&
                            transaction.getUserId().equals(userId)

            );
        double total = transactionService.calculateTotal(categoryTrans);
            System.out.println(c +": "+ total);
        }

    }
    public void printYearToDateSummary(String userId, String year, ArrayList<Transaction> trans){
    //Loop through transactions,
        double totalIncome = 0, totalExpenses = 0;
        for(Transaction t: trans) {
            if (t != null) {
                String date = t.getDate();
                String[] parts = date.split("-");

                if (parts[0].equals(year)) {
                    if (t.getType() == TransactionType.EXPENSE && t.getUserId().equals(userId)) {
                        totalExpenses += t.getAmount();
                        // calculate totalIncome (filter INCOME type),
                    } else if (t.getType() == TransactionType.INCOME && t.getUserId().equals(userId)) {
                        totalIncome += t.getAmount();
                    }
                }
            }
        }
        System.out.println("Total Income for "+year+": "+totalIncome+"\n"+
                "Total Expenses for "+year+": "+totalExpenses+"\n");
        System.out.println(totalIncome > totalExpenses ? "Total Savings for " + year+": " + (totalIncome - totalExpenses) : "no Savings this year :c");

    }
    public double calculateCompoundInterest(double principal, double rate, int years){
         if(years == 0){
             return principal;
         }else
             return calculateCompoundInterest(principal*(1+rate),rate,years -1);
    }
}
