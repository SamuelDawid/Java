package service;

import enums.Category;
import enums.TransactionType;
import model.MonthlyReport;
import model.Transaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportService {
    //Methods:
     public MonthlyReport generateMonthlyReport(String userId, Month month, ArrayList<Transaction> trans){

         Stream<Transaction> filter = trans.stream()
                 .filter(t -> t.getUserId().equals(userId) && t.getDate().split("-")[1].equals(String.format("%02d",month.getValue())));
         List<Transaction> filterList = filter.toList();

       double totalExpenses =  filterList.stream().filter(t -> t.getType().equals(TransactionType.EXPENSE)).mapToDouble(Transaction::getAmount).sum();
       double totalIncome = filterList.stream().filter(t->t.getType().equals(TransactionType.INCOME)).mapToDouble(Transaction::getAmount).sum();

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
        Stream<Transaction> filter = trans.stream()
                .filter(t->t.getDate().split("-")[0].equals(year) && t.getUserId().equals(userId));
        List<Transaction>filterList = filter.toList();
        double totalExpenses =  filterList.stream().filter(t -> t.getType().equals(TransactionType.EXPENSE)).mapToDouble(Transaction::getAmount).sum();
        double totalIncome = filterList.stream().filter(t->t.getType().equals(TransactionType.INCOME)).mapToDouble(Transaction::getAmount).sum();

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
