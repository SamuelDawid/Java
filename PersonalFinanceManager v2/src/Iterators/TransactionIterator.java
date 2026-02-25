package Iterators;

import model.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class TransactionIterator implements Iterator<Transaction> {
    List<Transaction> transactions;
    int currentIndex;
    boolean canRemove;
    Predicate<Transaction> filter;

    public TransactionIterator(List<Transaction> transactions, Predicate<Transaction> filter) {
        this.transactions = transactions;
        this.currentIndex = 0;
        this.canRemove = false;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < transactions.size()){
            if(filter.test(transactions.get(currentIndex)))
                return true;
            currentIndex++;
        }
        return false;

    }

    @Override
    public Transaction next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException("No more elements left");

        Transaction currentElement = transactions.get(currentIndex);
        currentIndex++;
        canRemove = true;

        return currentElement;
    }

    @Override
    public void remove() throws IllegalStateException {
        if (!canRemove)
            throw new IllegalStateException("IllegalStateException");

        transactions.remove(currentIndex - 1);
        currentIndex--;
        canRemove = false;

    }
}
