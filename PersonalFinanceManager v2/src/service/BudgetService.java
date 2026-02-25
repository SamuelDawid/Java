package service;

import enums.Category;
import enums.Months;
import model.Budget;

import java.util.ArrayList;

public class BudgetService {
    //Fields:
     public int budgetCount = 0;

    static int budgetID;

    public static int getBudgetID() {
        return budgetID;
    }

    public static void setBudgetID(int budgetID) {
        BudgetService.budgetID = budgetID;
    }
//Methods:
    //- setBudget(Budget b)

    public void setBudget(Budget b, ArrayList<Budget> budgets){
        budgetID++;
        b.setBudgetId(String.valueOf(budgetID));
        budgets.add(b);
        budgetCount++;

    }
    public Budget setBudget(){

        return null;
    }
   public Budget getBudget(String userId, Category cat, Months month,ArrayList<Budget> budgets){

        for(Budget b : budgets) {
            if (b != null) {
                if (b.getUserId().equals(userId)) {
                    if (b.getCategory().equals(cat) && b.getMonth().equals(month))
                        return b;
                }
            }
        }
        return null;

    }
    boolean isOverBudget(String userId, Category cat, String month, double spent,ArrayList<Budget> budgets){

        for(Budget b : budgets) {
            if (b != null) {
                if (b.getUserId().equals(userId)) {
                    if (b.getCategory().equals(cat) && b.getMonth().equals(Months.valueOf(month))) {

                        if (b.calculateRemaining(spent) > 0)
                            return false;

                    }
                }
            }
        }
        return true;

    }
}
