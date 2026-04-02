package com.YellowFlash.Bakery_v1.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
@Getter
@Service
public class CoffeeService {
    List<String> menu = List.of("Latte", "Espresso", "Cappuccino");


    public String prepareCoffee(String type){
        return "Making " + type;
    }

}
