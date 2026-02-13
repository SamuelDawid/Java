package service;

import enums.Category;
import enums.Months;
import model.Budget;

public class BudgetService {
    //Fields:
     int budgetCount = 0;

    static int budgetID;
    //Methods:
    //- setBudget(Budget b)

    public void setBudget(Budget b,Budget[] budgets){
        budgetID++;
        b.setBudgetId(String.valueOf(budgetID));
        budgets[budgetCount] = b;
        budgetCount++;

    }
   public Budget getBudget(String userId, Category cat, Months month,Budget[] budgets){

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
    boolean isOverBudget(String userId, Category cat, String month, double spent,Budget[] budgets){

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
