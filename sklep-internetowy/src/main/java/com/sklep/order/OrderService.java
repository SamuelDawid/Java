package com.sklep.order;

import com.sklep.cart.Cart;
import com.sklep.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orderHistory = new ArrayList<>();
    private HashSet<Integer> orderId = new HashSet<>();
    private Random rnd = new Random();

    public List<Order> getOrdersSortedByTotal(){
        return orderHistory.stream().sorted(Comparator.comparing(Order::total)).collect(Collectors.toList());
    }
    public double getTotalSpent(){
        return orderHistory.stream().mapToDouble(Order::total).sum();
    }
    public Optional<Order> getMostExpensiveOrder(){
        //return getOrdersSortedByTotal().reversed().stream().findFirst();
        return orderHistory.stream().max(Comparator.comparing(Order::total));
    }
    public List<Order> getOrderByDate(){
        return orderHistory.stream().sorted(Comparator.comparing(Order::dateTime)).collect(Collectors.toList());
    }
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public boolean placeOrder(Cart cart){
        if(cart != null){
            // create order
            orderHistory.add(new Order(
                    randomOrderNumber(),
                    cart.getProductLinkedList(),
                    LocalDateTime.now(),
                    cart.getTotal()
            ));
            return true;
        }
        throw new IllegalArgumentException();
    }


    private int randomOrderNumber(){
        while (true){
           int generate =  rnd.nextInt(0,999999999);
            if(!orderId.contains(generate)){
                orderId.add(generate);
                return generate;
            }
        }

    }
}
