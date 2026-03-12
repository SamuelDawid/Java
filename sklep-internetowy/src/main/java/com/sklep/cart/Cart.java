package com.sklep.cart;

import com.sklep.model.Product;

import java.util.LinkedList;
import java.util.Optional;

public class Cart {
    LinkedList<Product> productLinkedList = new LinkedList<>();
    CartIterator iterator = new CartIterator(productLinkedList);


    boolean addProduct(Product product){
    Optional<Product> productToAdd = Optional.ofNullable(product);
        if(productToAdd.isPresent()){
            productLinkedList.add(productToAdd.get());
            return true;
        }

        return false;
    }

    boolean removeProduct(Product product){
        Optional<Product> productToRemove = Optional.ofNullable(product);
        if(productToRemove.isPresent()){
            productLinkedList.remove(productToRemove.get());
            return true;
        }
        return false;
    }
    Double getTotal(){
        return productLinkedList.stream().mapToDouble(Product::getPrice).sum();
    }
}
