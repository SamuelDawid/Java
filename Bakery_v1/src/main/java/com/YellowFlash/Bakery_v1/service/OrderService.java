package com.YellowFlash.Bakery_v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class OrderService {
    private final BakeryService bakeryService;
    private final CoffeeService coffeeService;
    //private final LinkedHashMap<String, List<String>> menu;

    public void printMenu(){
        System.out.println("Pieczywo: " + bakeryService.getMenu());
        System.out.println("Kawa: " + coffeeService.getMenu());
    }
    public boolean isValidChoice(String choice,List<String> menu){
        return menu.contains(choice);
    }
    public String placeOrder(String food,String coffee) throws IllegalArgumentException {
        if(!isValidChoice(food,bakeryService.getMenu()) ||
                !isValidChoice(coffee,coffeeService.getMenu())
        ) {
            throw new IllegalArgumentException("Incorrect food choice");
        }
        String foodOrder = bakeryService.prepareOrder(food);
        String coffeeOrder =coffeeService.prepareCoffee(coffee);

        return foodOrder +"\n"+ coffeeOrder +"\n" +"Ready!";
    }
}
