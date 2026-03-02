package service;

import enums.Category;
import enums.TransactionType;
import model.MonthlyReport;
import model.Transaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
    //Methods:
     public MonthlyReport generateMonthlyReport(String userId, Month month, ArrayList<Transaction> trans){

         List<Transaction> filterList  = trans.stream()
                 .filter(t -> t.userId().equals(userId) && t.date().split("-")[1].equals(String.format("%02d",month.getValue()))).toList();

       double totalExpenses =  filterList.stream().filter(t -> t.type().equals(TransactionType.EXPENSE)).mapToDouble(Transaction::amount).sum();
       double totalIncome = filterList.stream().filter(t->t.type().equals(TransactionType.INCOME)).mapToDouble(Transaction::amount).sum();

        return new MonthlyReport(userId,month,totalIncome,totalExpenses,(totalIncome - totalExpenses));
     }
    public void printCategoryBreakdown(String userId,TransactionService transactionService){

        for(Category c: Category.values()){
            List<Transaction> categoryTrans = transactionService.getFilteredTransaction(
                    transaction -> transaction.category().equals(c) &&
                            transaction.userId().equals(userId)

            );
        double total = transactionService.calculateTotal(categoryTrans);
            System.out.println(c +": "+ total);
        }

    }
    public void printYearToDateSummary(String userId, String year, ArrayList<Transaction> trans){
    //Loop through transactions,
        List<Transaction>filterList = trans.stream()
                .filter(t->t.date().split("-")[0].equals(year) && t.userId().equals(userId)).toList();
        double totalExpenses =  filterList.stream().filter(t -> t.type().equals(TransactionType.EXPENSE)).mapToDouble(Transaction::amount).sum();
        double totalIncome = filterList.stream().filter(t->t.type().equals(TransactionType.INCOME)).mapToDouble(Transaction::amount).sum();

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
