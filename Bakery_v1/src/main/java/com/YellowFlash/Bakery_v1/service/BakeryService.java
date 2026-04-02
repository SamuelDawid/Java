package com.YellowFlash.Bakery_v1.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BakeryService {
    List<String> menu = List.of("Croissant","Bagel","Muffin");

    public String prepareOrder(String item){

        return "preparing " + item;
    }

    public List<String> getMenu() {
        return menu;
    }
}
