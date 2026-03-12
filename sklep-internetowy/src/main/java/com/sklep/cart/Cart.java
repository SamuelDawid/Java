package com.sklep.cart;

import com.sklep.model.Product;

import java.util.Iterator;
import java.util.LinkedList;

public class Cart implements Iterable<Product>{
    LinkedList<Product> productLinkedList = new LinkedList<>();

    public boolean addProduct(Product product){
        if(product != null){
            productLinkedList.add(product);
            return true;
        }

        throw new  IllegalArgumentException();
    }

    public boolean removeProduct(Product product){
        if(product != null){
            productLinkedList.remove(product);
            return true;
        }
        throw new  IllegalArgumentException();

    }
    public Double getTotal(){
        return productLinkedList.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public Iterator<Product> iterator() {
        return new CartIterator(productLinkedList);
    }

}
