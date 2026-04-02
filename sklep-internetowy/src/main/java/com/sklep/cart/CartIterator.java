package com.sklep.cart;

import com.sklep.model.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class CartIterator implements Iterator<Product> {
    LinkedList<Product> list;
    int index = 0;
    boolean canRemove = false;
    Predicate<Product> predicate;

//    public CartIterator(LinkedList<Product> list, Predicate<Product> predicate) {
//        this.list = list;
//        this.predicate = predicate;
//    }

    public CartIterator(LinkedList<Product> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public Product next() {
        if(hasNext()) {
            Product product = list.get(index);
            index++;
            canRemove = true;
            return product;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        if(canRemove){
            list.remove(list.get(index - 1));
        }
    }
}
