package Iterators;

import model.Budget;

import java.util.*;
import java.util.function.Predicate;

public class BudgetIterator implements Iterator<Budget> {
    List<Budget> budgetList;
    int currentIndex;
    boolean canRemove;
    Predicate<Budget> filter;

    public BudgetIterator(List<Budget> budgetList, Predicate<Budget> filter) {
        this.budgetList = budgetList;
        this.filter = filter;
        this.currentIndex = 0;
        this.canRemove = false;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < budgetList.size()) {
            if (filter.test(budgetList.get(currentIndex)))
                return true;
            currentIndex++;
        }
        return false;
    }

    @Override
    public Budget next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();

        Budget currentBudget = budgetList.get(currentIndex);
        currentIndex++;
        canRemove = true;
        return currentBudget;
    }

    @Override
    public void remove() {
        if (!canRemove)
            throw new IllegalStateException("IllegalStateException");

        budgetList.remove(currentIndex -1);
        currentIndex--;
        canRemove = false;
    }

}
