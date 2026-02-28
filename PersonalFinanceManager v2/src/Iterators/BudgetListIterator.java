package Iterators;

import model.Budget;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class BudgetListIterator implements ListIterator<Budget> {

    List<Budget> budgetList;
    int currentIndex, previousIndex;
    boolean canRemove;
    Predicate<Budget> filter;

    public BudgetListIterator(List<Budget> budgetList, Predicate<Budget> filter) {
        this.budgetList = budgetList;
        this.currentIndex = 0;
        this.previousIndex = 0;

        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < budgetList.size()){
           if(filter.test(budgetList.get(currentIndex)))
               return true;

            currentIndex++;
        }
        return false;
    }

    @Override
    public Budget next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();

        Budget currentBudget = budgetList.get(currentIndex);
        currentIndex++;
        canRemove = true;

        return currentBudget;
    }

    @Override
    public boolean hasPrevious() {
        for (int i = currentIndex -1; i >= 0 ; i--) {
            if(filter.test(budgetList.get(i)))
                return true;
        }
        return false;
    }

    @Override
    public Budget previous() throws NoSuchElementException{
        if(!hasPrevious())
            throw new NoSuchElementException();
        Budget previousBudget = budgetList.get(currentIndex -1);
        currentIndex--;
        canRemove = true;

        return  previousBudget;
    }

    @Override
    public int nextIndex() {
        return currentIndex + 1;
    }

    @Override
    public int previousIndex() {
        return currentIndex -1;
    }

    @Override
    public void remove() {
        if (!canRemove)
            throw new IllegalStateException("IllegalStateException");

        budgetList.remove(currentIndex -1);
        currentIndex--;
        canRemove = false;
    }

    @Override
    public void set(Budget budget) {

    }

    @Override
    public void add(Budget budget) {

    }
}
