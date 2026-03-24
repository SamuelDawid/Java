package com.YellowFlash.Bakery_v1.service;

import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    public String prepareCoffee(String type){
        return "Making" + type;
    }
}
