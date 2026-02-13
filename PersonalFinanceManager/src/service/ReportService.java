package service;

import enums.Category;
import enums.Months;
import enums.TransactionType;
import model.MonthlyReport;
import model.Transaction;
public class ReportService {
    //Methods:
     public MonthlyReport generateMonthlyReport(String userId, Months month,Transaction[] trans, TransactionService transactionService){
        //Get all transactions for user,
         double totalIncome = 0,totalExpenses = 0;

        for (Transaction t : trans){
            if(t != null){
                // calculate totalExpenses (filter EXPENSE type),
                if(t.getType() == TransactionType.EXPENSE){
                    totalExpenses+= t.getAmount();
                    // calculate totalIncome (filter INCOME type),
                }else if(t.getType() == TransactionType.INCOME){
                    totalIncome+= t.getAmount();
                }
            }
        }

        return new MonthlyReport(userId,month,totalIncome,totalExpenses,(totalIncome - totalExpenses));
     }
    public void printCategoryBreakdown(String userId, Transaction[] trans,TransactionService transactionService){

        for(Category c: Category.values()){
        Transaction[] categoryTrans = transactionService.getTransactionsByCategory(userId,c,trans);
        double total = transactionService.calculateTotal(categoryTrans);
            System.out.println(c +": "+ total);
        }

    }
    public void printYearToDateSummary(String userId, String year, Transaction[] trans){
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
}
