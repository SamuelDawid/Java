package com.YellowFlash.Bakery_v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class OrderService {
    private final BakeryService bakeryService;
    private final CoffeeService coffeeService;

    public String placeOrder(String food,String coffee){
        bakeryService.prepareOrder(food);
        coffeeService.prepareCoffee(coffee);

        return food + coffee + "Read!";
    }
}
